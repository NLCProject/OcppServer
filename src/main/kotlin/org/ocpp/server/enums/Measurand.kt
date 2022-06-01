package org.ocpp.server.enums

import org.isc.utils.annotations.GenerateTsModel

/**
 * Defines the measurand and unit.
 */
@GenerateTsModel
enum class Measurand(

    /**
     * Measure unit.
     */
    var unit: String
) {

    WattHour(unit = "Wh")
}
