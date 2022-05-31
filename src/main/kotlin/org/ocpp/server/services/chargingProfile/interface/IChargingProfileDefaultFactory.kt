package org.ocpp.server.services.chargingProfile.`interface`

import eu.chargetime.ocpp.model.core.ChargingProfile

/**
 * Factory to build a default charging profile.
 */
interface IChargingProfileDefaultFactory {

    /**
     * Returns a default charging profile.
     *
     * @return Charging profile.
     */
    fun build(): ChargingProfile
}
