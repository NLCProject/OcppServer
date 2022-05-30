package org.ocpp.server.services.requests

import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.Organisation
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.chargingProfile.`interface`.IChargingProfileDefaultFactory
import org.ocpp.server.services.requests.interfaces.IRemoteStartTransactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RemoteStartTransactionService @Autowired constructor(
    private val connectorRepository: ConnectorRepository,
    private val serverRequestService: IServerRequestService,
    private val transactionService: TransactionService,
    private val chargingProfileDefaultFactory: IChargingProfileDefaultFactory
) : IRemoteStartTransactionService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun start(connectorId: Int) {
        logger.info("Starting transaction ID '$connectorId' via remote")
        val optional = connectorRepository.findByExternalId(externalId = connectorId)
        if (optional.isPresent)
            throw Exception("Connector with external ID '$connectorId' not found")

        val profile = chargingProfileDefaultFactory.build()
        transactionService.createTransaction(
            connector = optional.get(),
            transactionId = profile.transactionId,
            reservationId = 0,
            timestamp = profile.validFrom,
            currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        )

        serverRequestService.remoteStartTransaction(
            connectorId = connectorId,
            idTag = Organisation.id,
            profile = profile
        )
    }
}
