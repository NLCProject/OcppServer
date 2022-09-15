package org.ocpp.server.services.requests.interfaces

import eu.chargetime.ocpp.model.core.ResetType

/**
 * Service sends event to reset client.
 */
interface IResetService {

    /**
     * Sends reset event. Closes all ongoing transactions
     *
     * @param type Type of reset.
     * @param sessionIndex .
     */
    fun reset(type: ResetType, sessionIndex: String)
}
