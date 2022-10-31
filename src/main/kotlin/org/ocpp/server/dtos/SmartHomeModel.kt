package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import org.isc.utils.models.NamedModel
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
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var identifier: String = String()

    /**
     * Session index of its current connection.
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var sessionIndex: String = String()

    /**
     *
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var idTag: String = String()

    /**
     * Boolean if smart home has been authorized.
     */
    var authorized: Boolean = false

    /**
     * Image of the smart home.
     */
    var image: ImageModel? = null

    /**
     * Current status.
     */
    var status: SmartHomeStatus = SmartHomeStatus.Online

    /**
     *
     */
    var availableCommands: List<NamedModel> = emptyList()
}
