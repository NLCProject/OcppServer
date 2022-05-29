package org.ocpp.server.entities.notification

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationFilterService : FilterService<NotificationEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        emptyList()

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
