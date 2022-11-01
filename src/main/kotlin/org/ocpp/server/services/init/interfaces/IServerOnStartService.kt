package org.ocpp.server.services.init.interfaces

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

/**
 * Starts OCPP server on start up.
 */
interface IServerOnStartService {

    /**
     * Starts OCPP server on start up and fires ready event.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun startServer()
}
