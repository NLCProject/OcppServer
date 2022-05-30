package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel

/**
 *
 */
@GenerateTsModel
class SampledValueModel : IscModel() {

    /**
     *
     */
    var valueData: String = String()

    /**
     *
     */
    var contextData: String = String()

    /**
     *
     */
    var formatData: ValueFormat = ValueFormat.Raw

    /**
     *
     */
    var measurand: String = String()

    /**
     *
     */
    var phaseData: String = String()

    /**
     * Should always be Cable by an house.
     */
    var location: Location = Location.Cable

    /**
     *
     */
    var unit: String = String()

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var meterValueId: String = String()
}
