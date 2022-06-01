package test.server.repositoryTest

import org.isc.utils.tests.util.DataComparatorUtil
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.ocpp.server.Application
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class MeterValueRepositoryTest : RepositoryServiceTest<MeterValueEntity>() {

    @Autowired
    private lateinit var repositoryService: MeterValueRepository

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

    override fun compareEntitiesAndThrow(entity: MeterValueEntity, savedEntity: MeterValueEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): MeterValueEntity =
        testHelperService.createMeterValue(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: MeterValueEntity) { }
}
