package org.ocpp.server.services.requests.interfaces

/**
 *
 */
interface IRemoteStartTransactionService {

    /**
     *
     */
    fun start(connectorId: Int)
}
