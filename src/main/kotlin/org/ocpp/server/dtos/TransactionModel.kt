package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import javax.persistence.Column

/**
 *
 */
@GenerateTsModel
class TransactionModel : IscModel() {

    /**
     *
     */
    @Column
    var dateTimeStarted: String = String()

    /**
     *
     */
    @Column
    var dateTimeStopped: String = String()

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var connectorId: String = String()
}
