package org.ocpp.server.entities.image

import org.isc.utils.models.CurrentUser
import org.ocpp.server.entities.image.interfaces.IImageCreatorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageCreatorService @Autowired constructor(
    private val repositoryService: ImageRepository
) : IImageCreatorService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun create(currentUser: CurrentUser): String {
        logger.info("Creating image")
        val image = ImageEntity()
        image.organisationId = currentUser.organisationId
        return repositoryService.save(entity = image, currentUser = currentUser).id
    }
}
