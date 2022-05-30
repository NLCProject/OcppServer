package org.ocpp.server.services.requests.interfaces

/**
 *
 */
interface IRemoteStopTransactionService {

    /**
     *
     */
    fun stop(transactionId: Int)
}
