package org.ocpp.server.services.events.interfaces

import eu.chargetime.ocpp.model.core.MeterValue
import org.isc.utils.models.CurrentUser
import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.ocpp.server.entities.transaction.TransactionEntity
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
interface IMeterValueHandlingService {

    /**
     *
     */
    @Transactional
    @EventListener(MeterValuesRequestEvent::class)
    fun saveMeterValue(event: MeterValuesRequestEvent)

    /**
     *
     */
    fun createMeterValue(transaction: TransactionEntity, meterValue: MeterValue, currentUser: CurrentUser)
}
