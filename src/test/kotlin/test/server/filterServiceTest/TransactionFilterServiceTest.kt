package test.server.filterServiceTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.tests.FilterServiceTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.ocpp.server.Application
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionFilterService
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TransactionFilterServiceTest : FilterServiceTest<TransactionEntity>() {

    @Autowired
    private lateinit var repositoryService: TransactionRepository

    @Autowired
    private lateinit var filterService: TransactionFilterService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            filterService = filterService,
            repositoryService = repositoryService,
            instancesToCreate = 2,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    override fun createFilters(filters: FilterOptions) {
        assertTrue(filters.isEmpty())
    }

    override fun findAllHints(entities: List<TransactionEntity>) {
        val models = filterService.findAllHints(filter = FilterParameters(), currentUser = currentUser)
        assertTrue(models.isEmpty())
    }

    override fun createEntity(currentUser: CurrentUser): TransactionEntity =
        testHelperService.createTransaction(currentUser = currentUser)
}
