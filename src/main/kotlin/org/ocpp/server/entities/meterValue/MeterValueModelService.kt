package org.ocpp.server.entities.meterValue

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.utils.StringUtil
import org.ocpp.server.dtos.ConnectorModel
import org.ocpp.server.dtos.MeterValueModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeterValueModelService @Autowired constructor(
    filterService: MeterValueFilterService,
    private val repositoryService: MeterValueRepository
) : ModelService<MeterValueModel, MeterValueEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = MeterValueModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: MeterValueEntity,
        model: MeterValueModel,
        currentUser: CurrentUser
    ) { }

    override fun createAbstractModel(entity: MeterValueEntity, model: NamedModel, currentUser: CurrentUser) {
        model.firstLine.text = StringUtil.joinWithSeparatorWithSpace(
            separator = "|",
            entity.dateTimeCreated,
            entity.transaction.id,
            entity.transaction.connector.name
        )
    }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
