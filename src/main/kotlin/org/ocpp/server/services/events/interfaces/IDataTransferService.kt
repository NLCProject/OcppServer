package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IDataTransferService {

    /**
     * Incoming data.
     *
     * @param event .
     */
    @EventListener(ServerDataTransferRequestEvent::class)
    fun handleDataTransfer(event: ServerDataTransferRequestEvent)
}
