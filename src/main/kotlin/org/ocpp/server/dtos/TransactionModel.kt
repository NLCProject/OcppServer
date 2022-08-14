package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType

@GenerateTsModel
class TransactionModel : IscModel() {

    /**
     * Date when transaction is started.
     */
    var dateTimeStarted: String = String()

    /**
     * Date when transaction is stopped.
     */
    var dateTimeStopped: String = String()

    /**
     * Reason to stop.
     */
    var reasonToStop: Reason = Reason.Other

    /**
     * Current status.
     */
    var status: TransactionStatus = TransactionStatus.Ongoing

    /**
     * Current type.
     */
    var type: TransactionType = TransactionType.Outgoing

    /**
     * External handled ID.
     */
    var externalId: Int = 0

    /**
     * Optional reservation ID. If 0, it is not reserved by a connector.
     */
    var reservationId: Int = 0

    /**
     * Connector parent ID.
     */
    var connectorId: String = String()
}
