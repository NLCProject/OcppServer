package org.ocpp.server.entities.meterValue

import eu.chargetime.ocpp.model.core.MeterValue
import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class MeterValueService @Autowired constructor(
    repositoryService: MeterValueRepository,
    private val dateConversionService: IDateConversionService,
    private val transactionRepository: TransactionRepository,
    private val sampledValueService: SampledValueService
) : EntityService<MeterValueModel, MeterValueEntity>(
    entityClass = MeterValueEntity::class.java,
    repositoryService = repositoryService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Create meter value entity.
     *
     * @param transaction Transaction to which this entity belongs.
     * @param timestamp .
     * @param currentUser .
     */
    fun createMeterValue(
        transaction: TransactionEntity,
        timestamp: ZonedDateTime,
        currentUser: CurrentUser
    ): MeterValueEntity {
        logger.info("Saving meter value for new transaction")
        val meterValue = MeterValueModel()
        meterValue.transactionId = transaction.id
        meterValue.dateTimeCreated = dateConversionService.buildDateTimeString(date = timestamp)
        return saveEntity(model = meterValue, currentUser = currentUser)
    }

    /**
     * Create meter value entity.
     *
     * @param transaction Transaction to which this entity belongs.
     * @param meterValue .
     * @param currentUser .
     */
    fun createMeterValue(
        transaction: TransactionEntity,
        meterValue: MeterValue,
        currentUser: CurrentUser
    ) {
        logger.info("Saving meter value for new transaction")
        val entity = createMeterValue(
            transaction = transaction,
            timestamp = meterValue.timestamp,
            currentUser = currentUser
        )

        meterValue.sampledValue.forEach {
            sampledValueService.createSampledValue(meterValue = entity, sampledValue = it, currentUser = currentUser)
        }
    }

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
    ) { }

    override fun preDelete(entity: MeterValueEntity, currentUser: CurrentUser) {
        entity.sampledValues.forEach { sampledValueService.deleteEntity(id = it.id, currentUser = currentUser) }
    }

    override fun afterDelete(entity: MeterValueEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: MeterValueModel) { }
}
