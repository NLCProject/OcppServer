package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.genericCrudl.models.IscModel

/**
 * Meter value model.
 */
@GenerateTsModel
class MeterValueModel : IscModel() {

    /**
     * Date when the measure has been taken.
     */
    var dateTimeCreated: String = String()

    /**
     * Transaction parent ID.
     */
    var transactionId: String = String()
}
