package org.ocpp.server.services.requests

import eu.chargetime.ocpp.model.core.AvailabilityType
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.requests.interfaces.IChangeAvailabilityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChangeAvailabilityService @Autowired constructor(
    private val transactionService: TransactionService,
    private val serverRequestService: IServerRequestService
) : IChangeAvailabilityService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun changeAvailability(connectorId: Int, type: AvailabilityType, sessionIndex: String) {
        logger.info("Changing availability of connector ID '$connectorId' to type '$type' | " +
            "session index '$sessionIndex'")
        transactionService.closeAllOngoingTransactions(connectorId = connectorId)
        serverRequestService.changeAvailability(connectorId = connectorId, type = type, sessionIndex = sessionIndex)
    }
}
