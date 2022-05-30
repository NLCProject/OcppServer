package org.ocpp.server.services.requests.interfaces

import eu.chargetime.ocpp.model.core.AvailabilityType

/**
 *
 */
interface IChangeAvailabilityService {

    /**
     *
     */
    fun changeAvailability(connectorId: Int, type: AvailabilityType)
}
