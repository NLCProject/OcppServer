package org.ocpp.server.services.requests

import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.services.requests.interfaces.IUnlockConnectorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UnlockConnectorService @Autowired constructor(
    private val serverRequestService: IServerRequestService
) : IUnlockConnectorService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun unlockConnector(connectorId: Int) {
        logger.info("Unlocking connector ID '$connectorId'")
        serverRequestService.unlockConnector(connectorId = connectorId)
    }
}
