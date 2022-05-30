package org.ocpp.server.entities.sampledValue

import eu.chargetime.ocpp.model.core.SampledValue
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.enums.Measurand
import org.slf4j.LoggerFactory
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

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     *
     */
    fun createSampledValue(
        meterValue: MeterValueEntity,
        valueData: String,
        measurand: Measurand = Measurand.WattHour,
        valueFormat: ValueFormat = ValueFormat.SignedData,
        currentUser: CurrentUser
    ) {
        logger.info("Creating sampled value")
        val sampledValue = SampledValueModel()
        sampledValue.meterValueId = meterValue.id
        sampledValue.measurand = measurand.name
        sampledValue.unit = measurand.unit
        sampledValue.formatData = valueFormat
        sampledValue.valueData = valueData
        saveEntity(model = sampledValue, currentUser = currentUser)
    }

    /**
     *
     */
    fun createSampledValue(
        meterValue: MeterValueEntity,
        sampledValue: SampledValue,
        currentUser: CurrentUser
    ) {
        logger.info("Creating sampled value")
        val model = SampledValueModel()
        model.meterValueId = meterValue.id
        model.measurand = sampledValue.measurand
        model.unit = sampledValue.unit
        model.formatData = sampledValue.format
        model.valueData = sampledValue.value
        model.location = sampledValue.location
        model.contextData = sampledValue.context
        sampledValue.phase = sampledValue.phase
        saveEntity(model = model, currentUser = currentUser)
    }

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
