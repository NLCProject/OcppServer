package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IAuthorizeService {

    /**
     * Authorize smart home.
     *
     * @param event .
     */
    @EventListener(AuthorizeRequestEvent::class)
    fun authorize(event: AuthorizeRequestEvent)
}
