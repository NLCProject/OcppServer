package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType

@GenerateTsModel
class TransactionModel : IscModel() {

    /**
     * Date when transaction is started.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var dateTimeStarted: String = String()

    /**
     * Date when transaction is stopped.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var dateTimeStopped: String = String()

    /**
     * Reason to stop.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var reasonToStop: Reason = Reason.Other

    /**
     * Current status.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var status: TransactionStatus = TransactionStatus.Ongoing

    /**
     * Current type.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var type: TransactionType = TransactionType.Outgoing
    // TODO

    /**
     * External handled ID.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var externalId: Int = 0

    /**
     * Optional reservation ID. If 0, it is not reserved by a connector.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var reservationId: Int = 0

    /**
     * Connector parent ID.
     */
    var connectorId: String = String()
}
