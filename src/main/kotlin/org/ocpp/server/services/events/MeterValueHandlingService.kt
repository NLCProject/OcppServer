package org.ocpp.server.services.events

import eu.chargetime.ocpp.model.core.MeterValue
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.events.interfaces.IMeterValueHandlingService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeterValueHandlingService @Autowired constructor(
    private val meterValueService: MeterValueService,
    private val transactionRepository: TransactionRepository
) : IMeterValueHandlingService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun saveMeterValue(event: MeterValuesRequestEvent) {
        logger.info("Saving incoming meter values for session index '${event.sessionIndex}'")
        val optional = transactionRepository.findByExternalId(externalId = event.request.transactionId)
        if (!optional.isPresent)
            throw Exception("Transaction with ID '${event.request.transactionId}' not found")

        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        event.request.meterValue.forEach {
            meterValueService.createMeterValue(transaction = optional.get(), meterValue = it, currentUser = currentUser)
        }
    }
}
