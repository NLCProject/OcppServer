package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.ConnectorModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConnectorModelService @Autowired constructor(
    filterService: ConnectorFilterService,
    private val repositoryService: ConnectorRepository
) : ModelService<ConnectorModel, ConnectorEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = ConnectorModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: ConnectorEntity,
        model: ConnectorModel,
        currentUser: CurrentUser
    ) { }

    override fun createAbstractModel(entity: ConnectorEntity, model: NamedModel, currentUser: CurrentUser) {
        model.firstLine.text = entity.name
    }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
