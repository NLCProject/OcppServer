package test.server.repositoryTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.ocpp.server.Application
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class SmartHomeRepositoryTest : RepositoryServiceTest<SmartHomeEntity>() {

    @Autowired
    private lateinit var repositoryService: SmartHomeRepository

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

    override fun compareEntitiesAndThrow(entity: SmartHomeEntity, savedEntity: SmartHomeEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): SmartHomeEntity =
        testHelperService.createSmartHome(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: SmartHomeEntity) { }
}
