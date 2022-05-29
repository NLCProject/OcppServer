package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.StatusNotificationRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IStatusNotificationService {

    /**
     *
     */
    @EventListener(StatusNotificationRequestEvent::class)
    fun handleNotification(event: StatusNotificationRequestEvent)
}
