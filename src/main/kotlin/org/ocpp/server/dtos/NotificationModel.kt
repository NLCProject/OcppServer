package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.NotificationViewStatus

@GenerateTsModel
class NotificationModel : IscModel() {

    /**
     * Additional information.
     */
    var info: String = String()

    /**
     * Date when the notification has been created at the client.
     */
    var dateTimeCreated: String = String()

    /**
     * Vendor ID.
     */
    var vendorId: String = String()

    /**
     * Vendor error code.
     */
    var vendorErrorCode: String = String()

    /**
     * Current view status.
     */
    var viewStatus: NotificationViewStatus = NotificationViewStatus.New

    /**
     * Current client status.
     */
    var status: ChargePointStatus = ChargePointStatus.Available

    /**
     * Error code.
     */
    var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    /**
     * Connector parent ID.
     */
    var connectorId: String = String()
}
