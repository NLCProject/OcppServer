package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.ocpp.server.entities.connectors.interfaces.IConnectorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConnectorFilterService @Autowired constructor(
    private val repositoryService: ConnectorRepository
) : FilterService<ConnectorEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        repositoryService
            .findAll(currentUser = currentUser)
            .sortedBy { it.name }
            .map { SearchHintModel(id = it.id, name = it.name) }

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
