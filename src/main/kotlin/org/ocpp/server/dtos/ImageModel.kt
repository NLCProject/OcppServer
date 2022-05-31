package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.genericCrudl.models.IscModel

@GenerateTsModel
class ImageModel : IscModel() {

    /**
     * Base 64 string of original image.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var base64String: String? = null

    /**
     * Base 64 string of thumbnail.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var thumbnail: String? = null
}
