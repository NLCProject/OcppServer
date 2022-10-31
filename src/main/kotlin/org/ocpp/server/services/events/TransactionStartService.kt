package org.ocpp.server.services.events

import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.events.interfaces.ITransactionStartService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionStartService @Autowired constructor(
    private val transactionService: TransactionService,
    private val meterValueService: MeterValueService,
    private val sampledValueService: SampledValueService,
    private val connectorService: ConnectorService
) : ITransactionStartService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun startTransaction(event: StartTransactionRequestEvent) {
        logger.info("Handling start transaction request for session index '${event.sessionIndex}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val connector = connectorService.findOrCreateConnector(
            externalId = event.request.connectorId,
            sessionIndex = event.sessionIndex.toString(),
            currentUser = currentUser
        )

        val transaction = transactionService.createTransaction(
            connector = connector,
            transactionId = event.transactionId,
            reservationId = event.request.reservationId,
            timestamp = event.request.timestamp,
            currentUser = currentUser
        )

        val meterValue = meterValueService.createMeterValue(
            transaction = transaction,
            timestamp = DateTimeUtil.dateNow(),
            currentUser = currentUser
        )

        sampledValueService.createSampledValue(
            meterValue = meterValue,
            valueData = event.request.meterStart.toString(),
            currentUser = currentUser
        )
    }
}
