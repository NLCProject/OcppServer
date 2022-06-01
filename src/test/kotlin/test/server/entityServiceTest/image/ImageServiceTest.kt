package test.server.entityServiceTest.image

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.EntityServiceTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.ImageModel
import org.ocpp.server.entities.image.ImageEntity
import org.ocpp.server.entities.image.ImageModelService
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.entities.image.ImageService
import org.ocpp.server.services.test.ImageTestHelperService
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.ImageModelServiceTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ImageServiceTest : EntityServiceTest<ImageEntity, ImageModel>() {

    @Autowired
    private lateinit var repositoryService: ImageRepository

    @Autowired
    private lateinit var entityService: ImageService

    @Autowired
    private lateinit var modelService: ImageModelService

    @Autowired
    private lateinit var imageTestHelperService: ImageTestHelperService

    @Autowired
    private lateinit var testHelperService: TestHelperService

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
    override fun deleteEntityTest() { }

    @Test
    override fun saveEntityTest() {
        saveEntityTest(path = "images/test.jpg")
    }

    @Test
    fun saveEntityTest_BigImage() {
        saveEntityTest(path = "images/test-big.jpg")
    }

    override fun preDelete(currentUser: CurrentUser, entity: ImageEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: ImageEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: ImageEntity,
        model: ImageModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        ImageModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): ImageEntity =
        testHelperService.createImage(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): ImageModel {
        val image = createEntity(currentUser = currentUser)
        return modelService.findById(id = image.id, currentUser = currentUser)
    }

    fun saveEntityTest(path: String) {
        val model = ImageModel()
        model.base64String = imageTestHelperService.loadImageAsString(path = path)
        val image = entityService.saveEntity(model = model, currentUser = currentUser)

        assertTrue(image.thumbnail?.isNotEmpty() == true)
        assertTrue(image.base64String?.isNotEmpty() == true)
    }
}
