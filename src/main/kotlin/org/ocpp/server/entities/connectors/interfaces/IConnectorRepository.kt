package org.ocpp.server.entities.connectors.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.connectors.ConnectorEntity
import java.util.*

interface IConnectorRepository : ICrudlRepository<ConnectorEntity> {

    /**
     * Find a connector by its external ID.
     *
     * @param externalId .
     * @return Optional of entity.
     */
    fun findByExternalId(externalId: Int): Optional<ConnectorEntity>
}
