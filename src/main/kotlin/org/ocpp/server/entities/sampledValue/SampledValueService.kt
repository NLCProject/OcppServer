package org.ocpp.server.entities.sampledValue

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.entities.transaction.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SampledValueService @Autowired constructor(
    repositoryService: SampledValueRepository,
    private val meterValueRepository: MeterValueRepository
) : EntityService<SampledValueModel, SampledValueEntity>(
    entityClass = SampledValueEntity::class.java,
    repositoryService = repositoryService
) {

    override fun preSave(
        model: SampledValueModel,
        entity: SampledValueEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.meterValue = meterValueRepository.findById(id = model.meterValueId, currentUser = currentUser)
    }

    override fun afterSave(
        model: SampledValueModel,
        entity: SampledValueEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: SampledValueEntity, currentUser: CurrentUser) { }

    override fun afterDelete(entity: SampledValueEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: SampledValueModel) { }
}
