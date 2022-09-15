package org.ocpp.server.services.requests.interfaces

/**
 * Service sends event to unlock a connector.
 */
interface IUnlockConnectorService {

    /**
     * Sends unlock event.
     *
     * @param connectorId .
     * @param sessionIndex .
     */
    fun unlockConnector(connectorId: Int, sessionIndex: String)
}
