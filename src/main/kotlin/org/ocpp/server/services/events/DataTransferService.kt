package org.ocpp.server.services.events

import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.ocpp.server.services.events.interfaces.IDataTransferService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DataTransferService : IDataTransferService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleDataTransfer(event: ServerDataTransferRequestEvent) {
        logger.info("Handling data transfer for session index '${event.sessionIndex}'")
        // TODO
    }
}
