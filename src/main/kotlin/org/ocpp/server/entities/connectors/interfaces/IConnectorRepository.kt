package org.ocpp.server.entities.connectors.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.springframework.data.domain.Pageable
import java.util.*

interface IConnectorRepository : ICrudlRepository<ConnectorEntity> {

    /**
     * Find a connector by its external ID.
     *
     * @param externalId .
     * @return Optional of entity.
     */
    fun findByExternalId(externalId: Int): Optional<ConnectorEntity>

    /**
     * Returns all connectors with the given smart home ID.
     *
     * @param smartHomeId .
     * @param pageable .
     * @return List of entities.
     */
    fun findAllBySmartHomeId(smartHomeId: String, pageable: Pageable): List<ConnectorEntity>
}
