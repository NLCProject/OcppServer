package org.ocpp.server.services.requests.interfaces

/**
 * Service sends event to start transaction at client remotely.
 */
interface IRemoteStartTransactionService {

    /**
     * Sends start transaction event. Creates a new transaction.
     *
     * @param connectorId .
     */
    fun start(connectorId: Int)
}
