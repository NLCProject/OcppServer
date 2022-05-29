package org.ocpp.server.entities.transaction

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.TransactionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionModelService @Autowired constructor(
    filterService: TransactionFilterService,
    private val repositoryService: TransactionRepository
) : ModelService<TransactionModel, TransactionEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = TransactionModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: TransactionEntity,
        model: TransactionModel,
        currentUser: CurrentUser
    ) { }

    override fun createAbstractModel(entity: TransactionEntity, model: NamedModel, currentUser: CurrentUser) {
        model.firstLine.text = entity.dateTimeStarted
    }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
