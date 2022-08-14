package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.genericCrudl.models.IscModel

@GenerateTsModel
class SampledValueModel : IscModel() {

    /**
     * Value.
     */
    var valueData: String = String()

    /**
     * Context.
     */
    var contextData: String = String()

    /**
     * Format.
     */
    var formatData: ValueFormat = ValueFormat.Raw

    /**
     * Measurand.
     */
    var measurand: String = String()

    /**
     * Phase.
     */
    var phaseData: String = String()

    /**
     * Location.
     */
    var location: Location = Location.Cable

    /**
     * Unit.
     */
    var unit: String = String()

    /**
     * Meter value parent ID.
     */
    var meterValueId: String = String()
}
