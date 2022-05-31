package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.NotificationViewStatus

@GenerateTsModel
class NotificationModel : IscModel() {

    /**
     * Additional information.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var info: String = String()

    /**
     * Date when the notification has been created at the client.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var dateTimeCreated: String = String()

    /**
     * Vendor ID.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var vendorId: String = String()

    /**
     * Vendor error code.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var vendorErrorCode: String = String()

    /**
     * Current view status.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var viewStatus: NotificationViewStatus = NotificationViewStatus.New

    /**
     * Current client status.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var status: ChargePointStatus = ChargePointStatus.Available

    /**
     * Error code.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    /**
     * Connector parent ID.
     */
    var connectorId: String = String()
}
