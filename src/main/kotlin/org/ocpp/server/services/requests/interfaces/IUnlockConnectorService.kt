package org.ocpp.server.services.requests.interfaces

/**
 * Service sends event to unlock a connector.
 */
interface IUnlockConnectorService {

    /**
     * Sends unlock event.
     *
     * @param connectorId .
     */
    fun unlockConnector(connectorId: Int)
}
