package org.ocpp.server.services.requests.interfaces

/**
 * Service sends event to stop transaction at client remotely.
 */
interface IRemoteStopTransactionService {

    /**
     * Sends stop transaction event. Closes affected transaction.
     *
     * @param transactionId .
     */
    fun stop(transactionId: Int)
}
