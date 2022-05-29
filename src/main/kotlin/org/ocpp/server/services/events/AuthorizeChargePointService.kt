package org.ocpp.server.services.events

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.ocpp.server.configuration.Organisation
import org.ocpp.server.dtos.ConnectorModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.services.events.interfaces.IAuthorizeChargePointService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorizeChargePointService @Autowired constructor(
    private val connectorService: ConnectorService,
    private val connectorRepository: ConnectorRepository,
    private val smartHomeRepository: SmartHomeRepository
) : IAuthorizeChargePointService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun authorize(event: AuthorizeRequestEvent) {
        logger.info("Authorize request received for session index '${event.sessionIndex}'")
        val optional = smartHomeRepository.findBySessionIndex(sessionIndex = event.sessionIndex.toString())
        if (optional.isPresent)
            registerChargePoint(event = event, smartHome = optional.get())
    }

    private fun registerChargePoint(event: AuthorizeRequestEvent, smartHome: SmartHomeEntity) {
        logger.info("Registering new charge point for smart home ID '${smartHome.id}'")
        val idTag = event.request.idTag

        if (isConnectorExisting(idTag = idTag))
            return logger.warn("Connector ID tag '$idTag' already existing for smart home ID '${smartHome.id}'")

        val connector = ConnectorModel()
        connector.idTag = idTag
        connector.name = idTag
        connector.smartHomeId = smartHome.id
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        connectorService.saveEntity(model = connector, currentUser = currentUser)
    }

    private fun isConnectorExisting(idTag: String): Boolean = connectorRepository.findByIdTag(idTag = idTag).isPresent
}
