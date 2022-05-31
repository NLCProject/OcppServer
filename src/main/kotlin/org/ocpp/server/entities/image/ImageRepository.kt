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
     * Find thumbnail by its ID.
     *
     * @param id .
     * @return Byte array of thumbnail. May be null.
     */
    fun findThumbnail(id: String, currentUser: CurrentUser): String? {
        val optional = repository.findThumbnailById(id = id)
        if (optional.isPresent)
            return String(optional.get())

        return null
    }
}
