package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.services.RepositoryService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.entities.connectors.interfaces.IConnectorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConnectorRepository @Autowired constructor(
    private val repository: IConnectorRepository
) : RepositoryService<ConnectorEntity>(repository = repository) {

    /**
     * Find a connector by its external ID.
     *
     * @param externalId .
     * @return Optional of entity.
     */
    fun findByExternalId(externalId: Int): Optional<ConnectorEntity> =
        repository.findByExternalId(externalId = externalId)

    /**
     * Returns all power chargers with the given smart home ID.
     *
     * @param smartHomeId .
     * @param page Page size. Request parameter.
     * @param currentUser .
     * @return List of entities.
     */
    fun findAllBySmartHomeId(smartHomeId: String, page: Int, currentUser: CurrentUser): List<ConnectorEntity> {
        checkFeatureAndThrow(currentUser = currentUser)
        return repository.findAllBySmartHomeId(
            smartHomeId = smartHomeId,
            pageable = paginationService.build(page = page)
        )
    }
}
