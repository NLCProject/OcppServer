package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.HeartbeatRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IHeartbeatService {

    /**
     * Heartbeat.
     *
     * @param event .
     */
    @EventListener(HeartbeatRequestEvent::class)
    fun heartbeat(event: HeartbeatRequestEvent)
}
