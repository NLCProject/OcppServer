package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IAuthorizeService {

    /**
     *
     */
    @EventListener(AuthorizeRequestEvent::class)
    fun authorize(event: AuthorizeRequestEvent)
}
