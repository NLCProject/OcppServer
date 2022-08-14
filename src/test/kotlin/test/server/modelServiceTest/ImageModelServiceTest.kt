package test.server.modelServiceTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.tests.ModelServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.ImageModel
import org.ocpp.server.entities.image.ImageEntity
import org.ocpp.server.entities.image.ImageModelService
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ImageModelServiceTest : ModelServiceTest<ImageEntity, ImageModel>() {

    @Autowired
    private lateinit var repositoryService: ImageRepository

    @Autowired
    private lateinit var modelService: ImageModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            repositoryService = repositoryService,
            modelService = modelService,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    @Test
    override fun findAllPageable_withFilter() {
        createEntity(currentUser = currentUser)
        createEntity(currentUser = currentUser)

        Assertions.assertThrows(NotImplementedError::class.java) {
            modelService.findAllPageable(filter = FilterParameters(), page = 0, currentUser = currentUser)
        }
    }

    @Test
    override fun checkAbstractIcons() { }

    override fun compareEntityAndAbstractModelAndThrow(entity: ImageEntity, model: NamedModel) {
        assertFalse(model.firstLine.translate)
        assertFalse(model.secondLine.translate)
        assertFalse(model.thirdLine.translate)
        assertTrue(model.firstLine.text.isEmpty())
        assertTrue(model.secondLine.text.isEmpty())
        assertEquals(model.thumbnail?.isNotEmpty(), true)
        assertTrue(model.userId.isEmpty())
        assertNull(model.data)
        assertTrue(model.thirdLine.text.isEmpty())
    }

    override fun compareEntityAndModelAndThrow(entity: ImageEntity, model: ImageModel) {
        DataComparatorUtil.compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): ImageEntity =
        testHelperService.createImage(currentUser = currentUser)
}
