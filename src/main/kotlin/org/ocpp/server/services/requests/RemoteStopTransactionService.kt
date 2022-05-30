package org.ocpp.server.services.requests

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.services.requests.interfaces.IRemoteStopTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RemoteStopTransactionService @Autowired constructor(
    private val transactionService: TransactionService,
    private val serverRequestService: IServerRequestService
) : IRemoteStopTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun stop(transactionId: Int) {
        logger.info("Stopping transaction ID '$transactionId' via remote")
        transactionService.updateTransaction(
            transactionId = transactionId,
            timestamp = DateTimeUtil.dateNow(),
            reason = Reason.Remote,
            status = TransactionStatus.Finished,
            currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        )

        serverRequestService.remoteStopTransaction(transactionId = transactionId)
    }
}
