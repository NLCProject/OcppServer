package test.server.entityServiceTest.smartHome

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.EntityServiceTest
import org.isc.utils.utils.Ids
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeModelService
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.SmartHomeModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.random.Random
import kotlin.test.assertFalse

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class SmartHomeServiceTest : EntityServiceTest<SmartHomeEntity, SmartHomeModel>() {

    @Autowired
    private lateinit var repositoryService: SmartHomeRepository

    @Autowired
    private lateinit var entityService: SmartHomeService

    @Autowired
    private lateinit var modelService: SmartHomeModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var connectorRepository: ConnectorRepository

    @Autowired
    private lateinit var imageRepository: ImageRepository

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
        entityManager.flush()
        entityManager.refresh(smartHome)
        entityService.deleteEntity(id = smartHome.id, currentUser = currentUser)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertFalse(connectorOptional.isPresent)

        val imageOptional = imageRepository.findByIdOptional(id = smartHome.imageId)
        assertFalse(imageOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val connector = testHelperService.createConnector(currentUser = currentUser)
        val model = SmartHomeModel()
        model.name = Ids.getRandomId()
        model.identifier = Ids.getRandomId()
        model.sessionIndex = Ids.getRandomId()
        model.authorized = Random.nextBoolean()
        model.status = SmartHomeStatus.values().random()
        model.organisationId = currentUser.organisationId

        val smartHome = entityService.saveEntity(model = model, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(smartHome)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertTrue(connectorOptional.isPresent)

        val imageOptional = imageRepository.findByIdOptional(id = smartHome.imageId)
        assertTrue(imageOptional.isPresent)

        model.id = smartHome.id
        compareEntityAndModelAndThrow(model = model, entity = smartHome, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: SmartHomeEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: SmartHomeEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: SmartHomeEntity,
        model: SmartHomeModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        SmartHomeModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): SmartHomeEntity =
        testHelperService.createSmartHome(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): SmartHomeModel {
        val smartHome = createEntity(currentUser = currentUser)
        return modelService.findById(id = smartHome.id, currentUser = currentUser)
    }
}
