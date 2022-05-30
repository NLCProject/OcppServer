package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IDataTransferService {

    /**
     *
     */
    @EventListener(ServerDataTransferRequestEvent::class)
    fun handleDataTransfer(event: ServerDataTransferRequestEvent)
}
