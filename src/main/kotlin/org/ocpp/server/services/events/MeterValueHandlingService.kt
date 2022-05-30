package org.ocpp.server.services.events

import eu.chargetime.ocpp.model.core.MeterValue
import eu.chargetime.ocpp.model.core.SampledValue
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
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
    private val dateConversionService: IDateConversionService,
    private val meterValueService: MeterValueService,
    private val sampledValueService: SampledValueService,
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
            createMeterValue(transaction = optional.get(), meterValue = it, currentUser = currentUser)
        }
    }

    override fun createMeterValue(
        transaction: TransactionEntity,
        meterValue: MeterValue,
        currentUser: CurrentUser
    ) {
        logger.info("Saving meter value for new transaction")
        val model = MeterValueModel()
        model.transactionId = transaction.id
        model.dateTimeCreated = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())

        val entity = meterValueService.saveEntity(model = model, currentUser = currentUser)

        meterValue.sampledValue.forEach {
            createSampledValue(meterValueEntity = entity, sampledValue = it, currentUser = currentUser)
        }
    }

    private fun createSampledValue(
        meterValueEntity: MeterValueEntity,
        sampledValue: SampledValue,
        currentUser: CurrentUser
    ) {
        logger.info("Saving sampled value for existing transaction")
        val model = SampledValueModel()
        model.meterValueId = meterValueEntity.id
        model.measurand = sampledValue.measurand
        model.unit = sampledValue.unit
        model.formatData = sampledValue.format
        model.valueData = sampledValue.value
        model.location = sampledValue.location
        model.contextData = sampledValue.context
        sampledValue.phase = sampledValue.phase
        sampledValueService.saveEntity(model = model, currentUser = currentUser)
    }
}
