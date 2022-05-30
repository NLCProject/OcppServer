package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.ClientConnectedOnServerEvent
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
    @EventListener(ClientConnectedOnServerEvent::class)
    fun onClientConnected(event: ClientConnectedOnServerEvent)
}
