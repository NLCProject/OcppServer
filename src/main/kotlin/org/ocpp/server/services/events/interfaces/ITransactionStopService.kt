package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface ITransactionStopService {

    /**
     *
     */
    @EventListener(StopTransactionRequestEvent::class)
    fun stopTransaction(event: StopTransactionRequestEvent)
}
