package org.ocpp.server.services.events

import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.events.interfaces.IStopTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class StopTransactionService @Autowired constructor(
    private val transactionRepository: TransactionRepository,
    private val dateConversionService: IDateConversionService
): IStopTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun stopTransaction(event: StopTransactionRequestEvent) {
        logger.info("Handling stop transaction request for session index '${event.sessionIndex}'")
        val optional = transactionRepository.findByExternalId(externalId = event.request.transactionId)
        if (!optional.isPresent)
            throw Exception("Transaction with external ID '${event.request.transactionId}' not found")

        val transaction = optional.get()
        transaction.dateTimeStopped = dateConversionService.buildDateTimeString(date = event.request.timestamp)
        transaction.reasonToStop = event.request.reason

        // TODO
        event.request.transactionData
        event.request.meterStop

        // TODO verify ID tag. And do it globally -> for authorization service too!
        event.request.idTag
    }
}
