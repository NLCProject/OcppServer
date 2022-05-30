package org.ocpp.server.services.events

import org.ocpp.client.event.server.request.BootNotificationRequestEvent
import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.ocpp.server.services.events.interfaces.IBootNotificationService
import org.ocpp.server.services.events.interfaces.IDataTransferService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BootNotificationService : IBootNotificationService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleNotification(event: BootNotificationRequestEvent) {
        logger.info("Handling boot notification for session index '${event.sessionIndex}'")
        // TODO
    }
}
