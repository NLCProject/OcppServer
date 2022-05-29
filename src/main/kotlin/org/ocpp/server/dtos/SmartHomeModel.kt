package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel

/**
 *
 */
@GenerateTsModel
class SmartHomeModel : IscModel() {

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var name: String = String()

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var identifier: String = String()

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var sessionIndex: String = String()

    /**
     *
     */
    var authorized: Boolean = false

    /**
     *
     */
    var image: ImageModel? = null
}
