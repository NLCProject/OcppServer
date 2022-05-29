package org.ocpp.server.services.events

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.ocpp.client.Organisation
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.services.events.interfaces.IAuthorizeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class AuthorizeService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository
) : IAuthorizeService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun authorize(event: AuthorizeRequestEvent) {
        logger.info("Authorize request received for session index '${event.sessionIndex}'")
        val optional = smartHomeRepository.findBySessionIndex(sessionIndex = event.sessionIndex.toString())
        if (optional.isPresent)
            registerChargePoint(event = event, smartHome = optional.get())
    }

    private fun registerChargePoint(event: AuthorizeRequestEvent, smartHome: SmartHomeEntity) {
        logger.info("Registering new charge point for smart home ID '${smartHome.id}'")
        validateIdTagAndThrow(event = event)

        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        smartHome.authorized = true
        smartHomeRepository.save(entity = smartHome, currentUser = currentUser)
    }

    private fun validateIdTagAndThrow(event: AuthorizeRequestEvent) {
        if (Organisation.id != event.request.idTag)
            throw Exception("ID tag '${event.request.idTag}' is NOT valid")
    }
}
