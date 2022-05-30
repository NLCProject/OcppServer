package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface ITransactionStartService {

    /**
     *
     */
    @EventListener(StartTransactionRequestEvent::class)
    fun startTransaction(event: StartTransactionRequestEvent)
}
