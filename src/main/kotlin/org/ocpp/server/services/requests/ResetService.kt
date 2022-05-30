package org.ocpp.server.services.requests

import eu.chargetime.ocpp.model.core.ResetType
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.requests.interfaces.IResetService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResetService @Autowired constructor(
    private val transactionService: TransactionService,
    private val serverRequestService: IServerRequestService
) : IResetService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun reset(type: ResetType) {
        logger.info("Reset smart home")
        transactionService.closeAllOngoingTransactions()
        serverRequestService.reset(type = type)
    }
}
