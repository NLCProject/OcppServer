package org.ocpp.server.services.events

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.ClientConnectedOnServerEvent
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.events.interfaces.IClientRegisterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientRegisterService @Autowired constructor(
    private val smartHomeService: SmartHomeService,
    private val smartHomeRepository: SmartHomeRepository
) : IClientRegisterService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onClientConnected(event: ClientConnectedOnServerEvent) {
        logger.info("Handling client connected event")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val optional = smartHomeRepository.findBySessionIndexOrIdentifier(
            sessionIndex = event.sessionIndex.toString(),
            identifier = event.information.identifier
        )

        if (optional.isPresent)
            return updateConnectedClient(smartHome = optional.get(), event = event, currentUser = currentUser)

        registerNewClient(event = event, currentUser = currentUser)
    }

    private fun registerNewClient(event: ClientConnectedOnServerEvent, currentUser: CurrentUser) {
        logger.info("Registering new client with identifier '${event.information.identifier}'")
        val smartHome = SmartHomeModel()
        smartHome.name = event.information.identifier
        smartHome.status = SmartHomeStatus.Online
        smartHome.identifier = event.information.identifier
        smartHome.sessionIndex = event.sessionIndex.toString()
        smartHomeService.saveEntity(model = smartHome, currentUser = currentUser)
    }

    private fun updateConnectedClient(
        smartHome: SmartHomeEntity,
        event: ClientConnectedOnServerEvent,
        currentUser: CurrentUser
    ) {
        logger.info("Updating existing client with identifier '${smartHome.identifier}'")
        smartHome.lastHeartbeatTimestamp = System.currentTimeMillis()
        smartHome.sessionIndex = event.sessionIndex.toString()
        smartHome.status = SmartHomeStatus.Online
        smartHomeRepository.save(entity = smartHome, currentUser = currentUser)
    }
}
