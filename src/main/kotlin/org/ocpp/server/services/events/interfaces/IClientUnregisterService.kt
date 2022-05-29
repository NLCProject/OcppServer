package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.ClientSessionLostEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IClientUnregisterService {

    /**
     *
     */
    @EventListener(ClientSessionLostEvent::class)
    fun onClientDisconnected(event: ClientSessionLostEvent)
}
