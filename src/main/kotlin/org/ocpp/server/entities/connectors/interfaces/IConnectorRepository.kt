package org.ocpp.server.entities.connectors.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.connectors.ConnectorEntity
import java.util.*

/**
 *
 */
interface IConnectorRepository : ICrudlRepository<ConnectorEntity> {

    /**
     *
     */
    fun findByExternalId(externalId: Int): Optional<ConnectorEntity>
}
