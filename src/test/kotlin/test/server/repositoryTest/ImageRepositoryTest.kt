package test.server.repositoryTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.RepositoryServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.entities.image.ImageEntity
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ImageRepositoryTest : RepositoryServiceTest<ImageEntity>() {

    @Autowired
    private lateinit var repositoryService: ImageRepository

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
    fun findThumbnail() {
        val imageOne = testHelperService.createImage(currentUser = currentUser)
        testHelperService.createImage(currentUser = currentUser)

        val thumbnail = repositoryService.findThumbnail(id = imageOne.id, currentUser = currentUser)
        assertEquals(thumbnail?.isNotEmpty(), true)
        assertEquals(thumbnail, imageOne.thumbnail)
    }

    override fun compareEntitiesAndThrow(entity: ImageEntity, savedEntity: ImageEntity) {
        DataComparatorUtil.compareEntitiesAndThrow(entity1 = entity, entity2 = savedEntity)
    }

    override fun createEntity(currentUser: CurrentUser): ImageEntity =
        testHelperService.createImage(currentUser = currentUser)

    override fun preDelete(currentUser: CurrentUser, entity: ImageEntity) { }
}
