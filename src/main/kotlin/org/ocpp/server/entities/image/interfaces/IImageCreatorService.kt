package org.ocpp.server.entities.image.interfaces

import org.isc.utils.models.CurrentUser

/**
 *
 */
interface IImageCreatorService {

    /**
     *
     */
    fun create(currentUser: CurrentUser): String
}
