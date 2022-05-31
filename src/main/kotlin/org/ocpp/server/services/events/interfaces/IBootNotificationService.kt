package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.BootNotificationRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IBootNotificationService {

    /**
     *
     */
    @EventListener(BootNotificationRequestEvent::class)
    fun handleNotification(event: BootNotificationRequestEvent)
}
