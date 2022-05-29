package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.connectors.interfaces.IConnectorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConnectorRepository @Autowired constructor(
    private val repository: IConnectorRepository
) : RepositoryService<ConnectorEntity>(repository = repository) {

    /**
     *
     */
    fun findByIdTag(idTag: String): Optional<ConnectorEntity> = repository.findByIdTag(idTag = idTag)

    /**
     *
     */
    fun findByConnectorId(connectorId: Int): Optional<ConnectorEntity> =
        repository.findByConnectorId(connectorId = connectorId)
}