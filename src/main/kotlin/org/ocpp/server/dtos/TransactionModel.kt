package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType

/**
 *
 */
@GenerateTsModel
class TransactionModel : IscModel() {

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var dateTimeStarted: String = String()

    /**
     *
     */
    var dateTimeStopped: String = String()

    /**
     *
     */
    var reasonToStop: Reason = Reason.Other

    /**
     *
     */
    var status: TransactionStatus = TransactionStatus.Ongoing

    /**
     *
     */
    var type: TransactionType = TransactionType.Outgoing
    // TODO

    /**
     *
     */
    @ValidateContent(type = ValidationType.NotZeroOrLower, required = true)
    var externalId: Int = 0

    /**
     *
     */
    @ValidateContent(type = ValidationType.NotLowerZero, required = true)
    var reservationId: Int = 0

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var connectorId: String = String()
}
