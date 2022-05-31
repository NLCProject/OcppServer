package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.SmartHomeStatus

@GenerateTsModel
class SmartHomeModel : IscModel() {

    /**
     * Smart home name.
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var name: String = String()

    /**
     * Identifier of the client.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var identifier: String = String()

    /**
     * Session index of its current connection.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var sessionIndex: String = String()

    /**
     * Boolean if smart home has been authorized.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var authorized: Boolean = false

    /**
     * Image of the smart home.
     */
    var image: ImageModel? = null

    /**
     * Current status.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var status: SmartHomeStatus = SmartHomeStatus.Online
}
