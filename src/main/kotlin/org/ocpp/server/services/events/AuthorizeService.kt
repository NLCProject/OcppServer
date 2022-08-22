package org.ocpp.server.services.events

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.authentication.IdTagAuthorizer
import org.ocpp.server.services.events.interfaces.IAuthorizeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorizeService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository
) : IAuthorizeService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun authorize(event: AuthorizeRequestEvent) {
        logger.info("Authorize request received for session index '${event.sessionIndex}'")
        IdTagAuthorizer.authorizeAndThrow(idTag = event.request.idTag)
        val optional = smartHomeRepository.findBySessionIndex(sessionIndex = event.sessionIndex.toString())
        if (!optional.isPresent)
            throw Exception("Smart home with session index '${event.sessionIndex}' not found")

        authorizeSmartHome(smartHome = optional.get())
    }

    private fun authorizeSmartHome(smartHome: SmartHomeEntity) {
        logger.info("Authorizing smart home ID '${smartHome.id}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        smartHome.lastHeartbeatTimestamp = System.currentTimeMillis()
        smartHome.status = SmartHomeStatus.Online
        smartHome.authorized = true
        smartHomeRepository.save(entity = smartHome, currentUser = currentUser)
    }
}
