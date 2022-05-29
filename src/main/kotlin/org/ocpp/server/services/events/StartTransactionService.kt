package org.ocpp.server.services.events

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.services.events.interfaces.IStartTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StartTransactionService @Autowired constructor(
    private val connectorService: ConnectorService
): IStartTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun startTransaction(event: StartTransactionRequestEvent) {
        logger.info("Handling start transaction request for session index '${event.sessionIndex}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val connector = connectorService.findOrCreateConnector(
            externalId = event.request.connectorId,
            sessionIndex = event.sessionIndex.toString(),
            currentUser = currentUser
        )

        // TODO
    }
}
