package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface ITransactionStartService {

    /**
     * Start transaction.
     *
     * @param event .
     */
    @EventListener(StartTransactionRequestEvent::class)
    fun startTransaction(event: StartTransactionRequestEvent)
}
