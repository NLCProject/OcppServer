package org.ocpp.server.entities.meterValue

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.springframework.stereotype.Service

@Service
class MeterValueFilterService : FilterService<MeterValueEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        emptyList()

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
