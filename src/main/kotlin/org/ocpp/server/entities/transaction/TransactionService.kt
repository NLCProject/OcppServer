package org.ocpp.server.entities.transaction

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.meterValue.MeterValueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService @Autowired constructor(
    repositoryService: TransactionRepository,
    private val connectorRepository: ConnectorRepository,
    private val meterValueService: MeterValueService
) : EntityService<TransactionModel, TransactionEntity>(
    entityClass = TransactionEntity::class.java,
    repositoryService = repositoryService
) {

    override fun preSave(
        model: TransactionModel,
        entity: TransactionEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.connector = connectorRepository.findById(id = model.connectorId, currentUser = currentUser)
    }

    override fun afterSave(
        model: TransactionModel,
        entity: TransactionEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: TransactionEntity, currentUser: CurrentUser) {
        entity.meterValues.forEach { meterValueService.deleteEntity(id = it.id, currentUser = currentUser) }
    }

    override fun afterDelete(entity: TransactionEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: TransactionModel) { }
}
