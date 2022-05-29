package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.springframework.stereotype.Service

@Service
class SmartHomeFilterService : FilterService<SmartHomeEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        emptyList()

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
