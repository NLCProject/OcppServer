package org.ocpp.server.dtos

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.RestrictModelToEntityConversion
import org.isc.utils.enums.ConversionRestriction
import org.isc.utils.genericCrudl.models.IscModel

@GenerateTsModel
class SampledValueModel : IscModel() {

    /**
     * Value.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var valueData: String = String()

    /**
     * Context.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var contextData: String = String()

    /**
     * Format.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var formatData: ValueFormat = ValueFormat.Raw

    /**
     * Measurand.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var measurand: String = String()

    /**
     * Phase.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var phaseData: String = String()

    /**
     * Location.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var location: Location = Location.Cable

    /**
     * Unit.
     */
    @RestrictModelToEntityConversion(restriction = ConversionRestriction.Ignore)
    var unit: String = String()

    /**
     * Meter value parent ID.
     */
    var meterValueId: String = String()
}
