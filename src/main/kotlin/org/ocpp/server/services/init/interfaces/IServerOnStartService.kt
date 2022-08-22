package org.ocpp.server.services.init.interfaces

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

/**
 *
 */
interface IServerOnStartService {

    /**
     *
     */
    @EventListener(ApplicationReadyEvent::class)
    fun startServer()
}
