package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.ClientSessionLostEvent
import org.springframework.context.event.EventListener

/**
 *
 */
interface IClientUnregisterService {

    /**
     *
     */
    @EventListener(ClientSessionLostEvent::class)
    fun onClientDisconnected(event: ClientSessionLostEvent)
}