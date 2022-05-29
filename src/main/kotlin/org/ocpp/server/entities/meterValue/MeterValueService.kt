package org.ocpp.server.entities.meterValue

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeterValueService @Autowired constructor(
    repositoryService: MeterValueRepository,
    private val transactionRepository: TransactionRepository,
    private val sampledValueService: SampledValueService
) : EntityService<MeterValueModel, MeterValueEntity>(
    entityClass = MeterValueEntity::class.java,
    repositoryService = repositoryService
) {

    override fun preSave(
        model: MeterValueModel,
        entity: MeterValueEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.transaction = transactionRepository.findById(id = model.transactionId, currentUser = currentUser)
    }

    override fun afterSave(
        model: MeterValueModel,
        entity: MeterValueEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        entity.sampledValues.forEach { sampledValueService.deleteEntity(id = it.id, currentUser = currentUser) }
    }

    override fun preDelete(entity: MeterValueEntity, currentUser: CurrentUser) { }

    override fun afterDelete(entity: MeterValueEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: MeterValueModel) { }
}
