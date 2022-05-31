package org.ocpp.server.services.requests.interfaces

import eu.chargetime.ocpp.model.core.AvailabilityType

/**
 * Service sends event to change the availability of a connector.
 */
interface IChangeAvailabilityService {

    /**
     * Sends change availability event. Closes all ongoing transactions for the given connector ID.
     *
     * @param connectorId .
     * @param type Type of availability.
     */
    fun changeAvailability(connectorId: Int, type: AvailabilityType)
}
