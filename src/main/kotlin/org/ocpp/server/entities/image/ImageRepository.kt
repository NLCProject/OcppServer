package org.ocpp.server.entities.image

import org.isc.utils.genericCrudl.services.RepositoryService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.entities.image.interfaces.IImageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageRepository @Autowired constructor(
    private val repository: IImageRepository
) : RepositoryService<ImageEntity>(repository = repository) {

    /**
     *
     */
    fun findThumbnail(id: String, currentUser: CurrentUser): String? {
        val imageOptional = if (currentUser.hasSuperRole()) {
            repository.findThumbnailById(id = id)
        } else {
            repository.findThumbnailByIdAndOrganisationId(id = id, organisationId = currentUser.organisationId)
        }

        if (imageOptional.isPresent)
            return String(imageOptional.get())

        return null
    }

    /**
     *
     */
    fun findAllWhereThumbnailIsNull(): List<ImageEntity> = repository.findByThumbnailIsNull()
}
