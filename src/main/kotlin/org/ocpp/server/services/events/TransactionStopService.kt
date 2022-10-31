package org.ocpp.server.services.events

import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.services.events.interfaces.ITransactionStopService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionStopService @Autowired constructor(
    private val meterValueService: MeterValueService,
    private val sampledValueService: SampledValueService,
    private val transactionService: TransactionService
) : ITransactionStopService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun stopTransaction(event: StopTransactionRequestEvent) {
        logger.info("Handling stop transaction request for session index '${event.sessionIndex}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val transaction = transactionService.updateTransaction(
            transactionId = event.request.transactionId,
            timestamp = event.request.timestamp,
            reason = event.request.reason,
            status = TransactionStatus.Finished,
            currentUser = currentUser
        )

        val meterValue = meterValueService.createMeterValue(
            transaction = transaction,
            timestamp = DateTimeUtil.dateNow(),
            currentUser = currentUser
        )

        sampledValueService.createSampledValue(
            meterValue = meterValue,
            valueData = event.request.meterStop.toString(),
            currentUser = currentUser
        )

        event.request.transactionData.forEach {
            meterValueService.createMeterValue(
                transaction = transaction,
                meterValue = it,
                currentUser = currentUser
            )
        }
    }
}
