package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.NotificationViewStatus

/**
 *
 */
@GenerateTsModel
class NotificationModel : IscModel() {

    /**
     *
     */
    var status: ChargePointStatus = ChargePointStatus.Available

    /**
     *
     */
    var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    /**
     *
     */
    var info: String = String()

    /**
     *
     */
    var dateTimeCreated: String = String()

    /**
     *
     */
    var vendorId: String = String()

    /**
     *
     */
    var vendorErrorCode: String = String()

    /**
     *
     */
    var viewStatus: NotificationViewStatus = NotificationViewStatus.New

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var connectorId: String = String()
}
