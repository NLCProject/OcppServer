package test.server.repositoryTest

import org.isc.utils.tests.util.DataComparatorUtil
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.ocpp.server.Application
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.notification.NotificationEntity
import org.ocpp.server.entities.notification.NotificationRepository
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import org.ocpp.server.entities.sampledValue.SampledValueRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class SampledValueRepositoryTest : RepositoryServiceTest<SampledValueEntity>() {

    @Autowired
    private lateinit var repositoryService: SampledValueRepository

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

    override fun compareEntitiesAndThrow(entity: SampledValueEntity, savedEntity: SampledValueEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): SampledValueEntity =
        testHelperService.createSampledValue(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: SampledValueEntity) { }
}
