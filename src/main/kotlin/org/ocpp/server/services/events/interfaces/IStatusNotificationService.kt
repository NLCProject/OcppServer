package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.StatusNotificationRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IStatusNotificationService {

    /**
     * Incoming status notification.
     *
     * @param event .
     */
    @EventListener(StatusNotificationRequestEvent::class)
    fun handleNotification(event: StatusNotificationRequestEvent)
}
