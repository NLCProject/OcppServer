package org.ocpp.server.services.events.interfaces

import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 * Server event.
 */
@Transactional
interface IMeterValueHandlingService {

    /**
     * Incoming meter value for a transaction.
     *
     * @param event .
     */
    @EventListener(MeterValuesRequestEvent::class)
    fun saveMeterValue(event: MeterValuesRequestEvent)
}
