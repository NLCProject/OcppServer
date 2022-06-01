package test.server.entityServiceTest.notification

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.EntityServiceTest
import org.isc.utils.utils.Ids
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.NotificationModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.notification.NotificationEntity
import org.ocpp.server.entities.notification.NotificationModelService
import org.ocpp.server.entities.notification.NotificationRepository
import org.ocpp.server.entities.notification.NotificationService
import org.ocpp.server.enums.NotificationViewStatus
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.NotificationModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class NotificationServiceTest : EntityServiceTest<NotificationEntity, NotificationModel>() {

    @Autowired
    private lateinit var repositoryService: NotificationRepository

    @Autowired
    private lateinit var entityService: NotificationService

    @Autowired
    private lateinit var modelService: NotificationModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var connectorRepository: ConnectorRepository

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
        val connector = testHelperService.createConnector(currentUser = currentUser)
        val notification = testHelperService.createNotification(connector = connector, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(notification)
        entityService.deleteEntity(id = notification.id, currentUser = currentUser)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertTrue(connectorOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val connector = testHelperService.createConnector(currentUser = currentUser)
        val model = NotificationModel()
        model.connectorId = connector.id
        model.info = Ids.getRandomId()
        model.dateTimeCreated = Ids.getRandomId()
        model.vendorId = Ids.getRandomId()
        model.vendorErrorCode = Ids.getRandomId()
        model.viewStatus = NotificationViewStatus.values().random()
        model.status = ChargePointStatus.values().random()
        model.errorCode = ChargePointErrorCode.values().random()
        model.organisationId = currentUser.organisationId

        val notification = entityService.saveEntity(model = model, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(notification)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertTrue(connectorOptional.isPresent)

        model.id = notification.id
        compareEntityAndModelAndThrow(model = model, entity = notification, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: NotificationEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: NotificationEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: NotificationEntity,
        model: NotificationModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        NotificationModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): NotificationEntity =
        testHelperService.createNotification(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): NotificationModel {
        val notification = createEntity(currentUser = currentUser)
        return modelService.findById(id = notification.id, currentUser = currentUser)
    }
}
