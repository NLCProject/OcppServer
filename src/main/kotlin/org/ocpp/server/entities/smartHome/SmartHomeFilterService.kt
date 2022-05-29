package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SmartHomeFilterService @Autowired constructor(
    private val repositoryService: SmartHomeRepository
) : FilterService<SmartHomeEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        repositoryService
            .findAll(currentUser = currentUser)
            .sortedBy { it.name }
            .map { SearchHintModel(id = it.id, name = it.name) }

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
