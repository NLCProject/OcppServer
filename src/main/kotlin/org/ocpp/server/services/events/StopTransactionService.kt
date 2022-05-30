package org.ocpp.server.services.events

import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.enums.Measurand
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.services.authentication.IdTagAuthorizer
import org.ocpp.server.services.events.interfaces.IMeterValueHandlingService
import org.ocpp.server.services.events.interfaces.IStopTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class StopTransactionService @Autowired constructor(
    private val meterValueService: MeterValueService,
    private val meterValueHandlingService: IMeterValueHandlingService,
    private val sampledValueService: SampledValueService,
    private val transactionRepository: TransactionRepository,
    private val dateConversionService: IDateConversionService
) : IStopTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun stopTransaction(event: StopTransactionRequestEvent) {
        logger.info("Handling stop transaction request for session index '${event.sessionIndex}'")
        IdTagAuthorizer.authorizeAndThrow(idTag = event.request.idTag)
        val optional = transactionRepository.findByExternalId(externalId = event.request.transactionId)
        if (!optional.isPresent)
            throw Exception("Transaction with external ID '${event.request.transactionId}' not found")

        val transaction = optional.get()
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        updateTransaction(transaction = transaction, event = event, currentUser = currentUser)
        createMeterStopValue(transaction = transaction, event = event, currentUser = currentUser)
        event.request.transactionData.forEach {
            meterValueHandlingService.createMeterValue(
                transaction = transaction,
                meterValue = it,
                currentUser = currentUser
            )
        }
    }

    private fun updateTransaction(
        transaction: TransactionEntity,
        event: StopTransactionRequestEvent,
        currentUser: CurrentUser
    ): TransactionEntity {
        logger.info("Updating transaction")
        transaction.dateTimeStopped = dateConversionService.buildDateTimeString(date = event.request.timestamp)
        transaction.reasonToStop = event.request.reason
        transaction.status = TransactionStatus.Finished
        return transactionRepository.save(entity = transaction, currentUser = currentUser)
    }

    private fun createMeterStopValue(
        transaction: TransactionEntity,
        event: StopTransactionRequestEvent,
        currentUser: CurrentUser
    ) {
        logger.info("Saving meter stop value for existing transaction")
        val meterValue = MeterValueModel()
        meterValue.transactionId = transaction.id
        createSampledValueOnStop(
            meterValue = meterValueService.saveEntity(model = meterValue, currentUser = currentUser),
            meterStop = event.request.meterStop,
            currentUser = currentUser
        )
    }

    private fun createSampledValueOnStop(
        meterValue: MeterValueEntity,
        meterStop: Int,
        currentUser: CurrentUser
    ) {
        logger.info("Saving sampled value on stop for existing transaction")
        val sampledValue = SampledValueModel()
        sampledValue.meterValueId = meterValue.id
        sampledValue.measurand = Measurand.WattHour.name
        sampledValue.unit = Measurand.WattHour.unit
        sampledValue.formatData = ValueFormat.SignedData
        sampledValue.valueData = meterStop.toString()
        sampledValueService.saveEntity(model = sampledValue, currentUser = currentUser)
    }
}
