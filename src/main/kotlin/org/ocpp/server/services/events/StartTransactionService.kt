package org.ocpp.server.services.events

import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.enums.Measurand
import org.ocpp.server.services.authentication.IdTagAuthorizer
import org.ocpp.server.services.events.interfaces.IStartTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StartTransactionService @Autowired constructor(
    private val dateConversionService: IDateConversionService,
    private val transactionService: TransactionService,
    private val meterValueService: MeterValueService,
    private val sampledValueService: SampledValueService,
    private val connectorService: ConnectorService
) : IStartTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun startTransaction(event: StartTransactionRequestEvent) {
        logger.info("Handling start transaction request for session index '${event.sessionIndex}'")
        IdTagAuthorizer.authorizeAndThrow(idTag = event.request.idTag)
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val connector = connectorService.findOrCreateConnector(
            externalId = event.request.connectorId,
            sessionIndex = event.sessionIndex.toString(),
            currentUser = currentUser
        )

        val transaction = createTransaction(connector = connector, event = event, currentUser = currentUser)
        val meterValue = createMeterValue(transaction = transaction, currentUser = currentUser)
        createSampledValue(meterValue = meterValue, event = event, currentUser = currentUser)
    }

    private fun createTransaction(
        connector: ConnectorEntity,
        event: StartTransactionRequestEvent,
        currentUser: CurrentUser
    ): TransactionEntity {
        logger.info("Saving new transaction")
        val transaction = TransactionModel()
        transaction.connectorId = connector.id
        transaction.dateTimeStarted = dateConversionService.buildDateTimeString(date = event.request.timestamp)
        transaction.externalId = event.transactionId
        transaction.reservationId = event.request.reservationId
        return transactionService.saveEntity(model = transaction, currentUser = currentUser)
    }

    private fun createMeterValue(transaction: TransactionEntity, currentUser: CurrentUser): MeterValueEntity {
        logger.info("Saving meter value for new transaction")
        val meterValue = MeterValueModel()
        meterValue.transactionId = transaction.id
        meterValue.dateTimeCreated = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        return meterValueService.saveEntity(model = meterValue, currentUser = currentUser)
    }

    private fun createSampledValue(
        meterValue: MeterValueEntity,
        event: StartTransactionRequestEvent,
        currentUser: CurrentUser
    ) {
        logger.info("Saving sampled value for new transaction")
        val sampledValue = SampledValueModel()
        sampledValue.meterValueId = meterValue.id
        sampledValue.measurand = Measurand.WattHour.name
        sampledValue.unit = Measurand.WattHour.unit
        sampledValue.formatData = ValueFormat.SignedData
        sampledValue.valueData = event.request.meterStart.toString()
        sampledValueService.saveEntity(model = sampledValue, currentUser = currentUser)
    }
}
