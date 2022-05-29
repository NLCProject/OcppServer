package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.ClientConnectedEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IClientRegisterService {

    /**
     *
     */
    @EventListener(ClientConnectedEvent::class)
    fun onClientConnected(event: ClientConnectedEvent)
}