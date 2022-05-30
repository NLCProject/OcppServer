package org.ocpp.server.services.chargingProfile.`interface`

import eu.chargetime.ocpp.model.core.ChargingProfile

/**
 *
 */
interface IChargingProfileDefaultFactory {

    /**
     *
     */
    fun build(): ChargingProfile
}
