package org.ocpp.server.entities.notification

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.NotificationModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationModelService @Autowired constructor(
    filterService: NotificationFilterService,
    private val repositoryService: NotificationRepository
) : ModelService<NotificationModel, NotificationEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = NotificationModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: NotificationEntity,
        model: NotificationModel,
        currentUser: CurrentUser
    ) { }

    override fun createAbstractModel(entity: NotificationEntity, model: NamedModel, currentUser: CurrentUser) { }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
