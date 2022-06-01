package test.server.entityServiceTest.connector

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.EntityServiceTest
import org.isc.utils.utils.Ids
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.ConnectorModel
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorModelService
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.notification.NotificationRepository
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.ConnectorModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.test.assertFalse

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ConnectorServiceTest : EntityServiceTest<ConnectorEntity, ConnectorModel>() {

    @Autowired
    private lateinit var repositoryService: ConnectorRepository

    @Autowired
    private lateinit var entityService: ConnectorService

    @Autowired
    private lateinit var modelService: ConnectorModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var smartHomeRepository: SmartHomeRepository

    @Autowired
    private lateinit var notificationRepository: NotificationRepository

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostConstruct
    fun init() {
        init(
            repositoryService = repositoryService,
            entityService = entityService,
            modelService = modelService,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    @Test
    override fun deleteEntityTest() {
        val smartHome = testHelperService.createSmartHome(currentUser = currentUser)
        val connector = testHelperService.createConnector(smartHome = smartHome, currentUser = currentUser)
        val notification = testHelperService.createNotification(connector = connector, currentUser = currentUser)
        val transaction = testHelperService.createTransaction(connector = connector, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(connector)
        entityService.deleteEntity(id = connector.id, currentUser = currentUser)

        val smartHomeOptional = smartHomeRepository.findByIdOptional(id = smartHome.id)
        assertTrue(smartHomeOptional.isPresent)

        val notificationOptional = notificationRepository.findByIdOptional(id = notification.id)
        assertFalse(notificationOptional.isPresent)

        val transactionOptional = transactionRepository.findByIdOptional(id = transaction.id)
        assertFalse(transactionOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val smartHome = testHelperService.createSmartHome(currentUser = currentUser)
        val model = ConnectorModel()
        model.smartHomeId = smartHome.id
        model.connectorName = Ids.getRandomId()
        model.externalId = Ids.getRandomIdentifier()
        model.organisationId = currentUser.organisationId

        val connector = entityService.saveEntity(model = model, currentUser = currentUser)
        val notification = testHelperService.createNotification(connector = connector, currentUser = currentUser)
        val transaction = testHelperService.createTransaction(connector = connector, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(connector)

        val smartHomeOptional = smartHomeRepository.findByIdOptional(id = smartHome.id)
        assertTrue(smartHomeOptional.isPresent)

        val notificationOptional = notificationRepository.findByIdOptional(id = notification.id)
        assertTrue(notificationOptional.isPresent)

        val transactionOptional = transactionRepository.findByIdOptional(id = transaction.id)
        assertTrue(transactionOptional.isPresent)

        model.id = connector.id
        compareEntityAndModelAndThrow(model = model, entity = connector, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: ConnectorEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: ConnectorEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: ConnectorEntity,
        model: ConnectorModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        ConnectorModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): ConnectorEntity =
        testHelperService.createConnector(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): ConnectorModel {
        val connector = createEntity(currentUser = currentUser)
        return modelService.findById(id = connector.id, currentUser = currentUser)
    }
}
