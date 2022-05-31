package org.ocpp.server.enums

/**
 * Defines the measurand and unit.
 */
enum class Measurand(

    /**
     * Measure unit.
     */
    var unit: String
) {

    WattHour(unit = "Wh")
}
