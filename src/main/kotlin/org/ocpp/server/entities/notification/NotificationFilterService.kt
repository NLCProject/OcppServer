package org.ocpp.server.entities.notification

import org.isc.utils.genericCrudl.services.FilterService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.models.filter.SearchHintModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationFilterService @Autowired constructor(
    private val repositoryService: NotificationRepository
) : FilterService<NotificationEntity>() {

    override fun createSearchHintModel(filter: FilterParameters, currentUser: CurrentUser): List<SearchHintModel> =
        repositoryService
            .findAll(currentUser = currentUser)
            .sortedBy { it.timestamp }
            .map { SearchHintModel(id = it.id, name = it.errorCode.name) }

    override fun getFilterModels(currentUser: CurrentUser): FilterOptions = FilterOptions()
}
