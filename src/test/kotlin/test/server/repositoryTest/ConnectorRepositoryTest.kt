package test.server.repositoryTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ConnectorRepositoryTest : RepositoryServiceTest<ConnectorEntity>() {

    @Autowired
    private lateinit var repositoryService: ConnectorRepository

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

    @Test
    fun findByExternalId() {
        val connector1 = testHelperService.createConnector(currentUser = currentUser)
        testHelperService.createConnector(currentUser = currentUser)
        val connectorOptional = repositoryService.findByExternalId(externalId = connector1.externalId)
        assertTrue(connectorOptional.isPresent)
        assertEquals(connector1.id, connectorOptional.get().id)
    }

    override fun compareEntitiesAndThrow(entity: ConnectorEntity, savedEntity: ConnectorEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): ConnectorEntity =
        testHelperService.createConnector(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: ConnectorEntity) { }
}
