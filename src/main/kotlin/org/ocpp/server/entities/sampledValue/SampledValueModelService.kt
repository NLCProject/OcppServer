package org.ocpp.server.entities.sampledValue

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.SampledValueModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SampledValueModelService @Autowired constructor(
    filterService: SampledValueFilterService,
    private val repositoryService: SampledValueRepository
) : ModelService<SampledValueModel, SampledValueEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = SampledValueModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: SampledValueEntity,
        model: SampledValueModel,
        currentUser: CurrentUser
    ) { }

    override fun createAbstractModel(entity: SampledValueEntity, model: NamedModel, currentUser: CurrentUser) { }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
