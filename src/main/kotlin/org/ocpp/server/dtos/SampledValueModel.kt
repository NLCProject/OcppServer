package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated

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
     *
     */
    var location: Location = Location.Body

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
