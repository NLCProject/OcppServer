package test.server.repositoryTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.ocpp.server.Application
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TransactionRepositoryTest : RepositoryServiceTest<TransactionEntity>() {

    @Autowired
    private lateinit var repositoryService: TransactionRepository

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            service = repositoryService,
            skipDelete = false,
            expectAlwaysOnlyOneEntity = false,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    override fun compareEntitiesAndThrow(entity: TransactionEntity, savedEntity: TransactionEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): TransactionEntity =
        testHelperService.createTransaction(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: TransactionEntity) { }
}
