package test.server.entityServiceTest.image

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.ocpp.server.Application
import org.ocpp.server.entities.image.interfaces.IImageDecoderService
import org.ocpp.server.enums.ImageSize
import org.ocpp.server.services.test.ImageTestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@Transactional
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ImageDecoderServiceTest {

    @Autowired
    private lateinit var service: IImageDecoderService

    @Autowired
    private lateinit var imageTestHelperService: ImageTestHelperService

    @Test
    fun decodeAndResize() {
        val resource = imageTestHelperService.loadImageAsString(path = "images/test.jpg")
        var newResource = service.decodeAndResize(base64String = resource, size = ImageSize.ORIGINAL)
        assertTrue(newResource.isNotEmpty())
        assertEquals(resource, newResource)

        newResource = service.decodeAndResize(base64String = resource, size = ImageSize.THUMBNAIL)
        assertTrue(newResource.isNotEmpty())
        assertNotEquals(resource, newResource)
    }

    @Test
    fun decodeAndResize_BigImage() {
        val resource = imageTestHelperService.loadImageAsString(path = "images/test-big.jpg")
        var newResource = service.decodeAndResize(base64String = resource, size = ImageSize.ORIGINAL)
        assertTrue(newResource.isNotEmpty())
        assertNotEquals(resource, newResource)

        newResource = service.decodeAndResize(base64String = resource, size = ImageSize.THUMBNAIL)
        assertTrue(newResource.isNotEmpty())
        assertNotEquals(resource, newResource)
    }
}
