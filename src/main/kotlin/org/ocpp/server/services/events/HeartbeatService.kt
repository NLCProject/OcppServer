package org.ocpp.server.services.events

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.event.server.request.HeartbeatRequestEvent
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.events.interfaces.IHeartbeatService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HeartbeatService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository
) : IHeartbeatService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun heartbeat(event: HeartbeatRequestEvent) {
        logger.info("Heartbeat received for session index '${event.sessionIndex}'")
        val optional = smartHomeRepository.findBySessionIndex(sessionIndex = event.sessionIndex.toString())
        if (optional.isPresent)
            updateHeartbeatTimestamp(smartHome = optional.get())
    }

    private fun updateHeartbeatTimestamp(smartHome: SmartHomeEntity) {
        logger.info("Updating heartbeat timestamp for smart home ID '${smartHome.id}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        smartHome.lastHeartbeatTimestamp = System.currentTimeMillis()
        smartHome.status = SmartHomeStatus.Online
        smartHomeRepository.save(entity = smartHome, currentUser = currentUser)
    }
}
