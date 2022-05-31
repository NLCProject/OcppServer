package org.ocpp.server.services.events.interfaces

import eu.chargetime.ocpp.model.core.MeterValue
import org.isc.utils.models.CurrentUser
import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.ocpp.server.entities.transaction.TransactionEntity
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
