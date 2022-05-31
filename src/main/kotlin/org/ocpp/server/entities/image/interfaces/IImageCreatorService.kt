package org.ocpp.server.entities.image.interfaces

import org.isc.utils.models.CurrentUser

/**
 * Service is used to create empty image entities.
 */
interface IImageCreatorService {

    /**
     * Create an image in the database.
     *
     * @param currentUser
     * @return Image entity ID.
     */
    fun create(currentUser: CurrentUser): String
}
