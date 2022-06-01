package test.server.entityServiceTest.image

import org.isc.utils.enums.Feature
import org.isc.utils.enums.Role
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.ocpp.server.Application
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.entities.image.interfaces.IImageCreatorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

@Transactional
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ImageCreatorServiceTest {

    @Autowired
    private lateinit var service: IImageCreatorService

    @Autowired
    private lateinit var repositoryService: ImageRepository

    private lateinit var currentUser: CurrentUser

    @BeforeEach
    fun createInstance() {
        currentUser = CurrentUserFactory.getCurrentUser(
            roles = mutableListOf(Role.Management),
            features = mutableListOf(Feature.Management)
        )
    }

    @Test
    fun create() {
        val id = service.create(currentUser = currentUser)
        val image = repositoryService.findById(id = id, currentUser = currentUser)
        assertEquals(currentUser.organisationId, image.organisationId)
    }
}
