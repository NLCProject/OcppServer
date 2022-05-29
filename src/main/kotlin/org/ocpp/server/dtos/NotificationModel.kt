package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel

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
    var dateCreated: String = String()

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
    @ValidateContent(type = ValidationType.NotZeroOrLower, required = true)
    var connectorId: String = String()
}
