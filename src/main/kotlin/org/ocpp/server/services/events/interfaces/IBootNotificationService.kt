package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.BootNotificationRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IBootNotificationService {

    /**
     * Notify about a boot.
     *
     * @param event .
     */
    @EventListener(BootNotificationRequestEvent::class)
    fun handleNotification(event: BootNotificationRequestEvent)
}
