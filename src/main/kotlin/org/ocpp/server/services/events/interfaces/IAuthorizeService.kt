package org.ocpp.server.services.events.interfaces

import eu.chargetime.ocpp.model.core.AuthorizeRequest
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
    @EventListener(AuthorizeRequest::class)
    fun authorize(event: AuthorizeRequestEvent)
}
