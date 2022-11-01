package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.genericCrudl.models.IscModel

/**
 * Image model.
 */
@GenerateTsModel
class ImageModel : IscModel() {

    /**
     * Base 64 string of original image.
     */
    var base64String: String? = null

    /**
     * Base 64 string of thumbnail.
     */
    var thumbnail: String? = null
}
