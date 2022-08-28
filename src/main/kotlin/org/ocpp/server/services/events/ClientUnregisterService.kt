package org.ocpp.server.services.events

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.ClientSessionLostEvent
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.services.events.interfaces.IClientUnregisterService
import org.ocpp.server.services.modbus.SmartHomeCommandCache
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientUnregisterService @Autowired constructor(
    private val smartHomeService: SmartHomeService,
    private val smartHomeRepository: SmartHomeRepository
) : IClientUnregisterService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onClientDisconnected(event: ClientSessionLostEvent) {
        logger.info("Handling client session lost event")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val optional = smartHomeRepository.findBySessionIndex(sessionIndex = event.sessionIndex.toString())

        if (optional.isPresent)
            removeClient(smartHome = optional.get(), currentUser = currentUser)
    }

    private fun removeClient(smartHome: SmartHomeEntity, currentUser: CurrentUser) {
        logger.info("Removing client ID '${smartHome.id}'")
        SmartHomeCommandCache.clearByIdTag(idTag = smartHome.idTag)
        smartHomeService.deleteEntity(id = smartHome.id, currentUser = currentUser)
    }
}
