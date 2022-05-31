package org.ocpp.server.entities.transaction

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.enums.TransactionStatus
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.ZonedDateTime

@Service
class TransactionService @Autowired constructor(
    private val dateConversionService: IDateConversionService,
    private val repositoryService: TransactionRepository,
    private val connectorRepository: ConnectorRepository,
    private val meterValueService: MeterValueService
) : EntityService<TransactionModel, TransactionEntity>(
    entityClass = TransactionEntity::class.java,
    repositoryService = repositoryService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Creates transaction entity.
     *
     * @param connector Connector to which this entity belongs.
     * @param transactionId .
     * @param reservationId .
     * @param timestamp .
     * @param currentUser .
     * @return Created entity.
     */
    fun createTransaction(
        connector: ConnectorEntity,
        transactionId: Int,
        reservationId: Int,
        timestamp: ZonedDateTime,
        currentUser: CurrentUser
    ): TransactionEntity {
        logger.info("Saving new transaction")
        val transaction = TransactionModel()
        transaction.connectorId = connector.id
        transaction.dateTimeStarted = dateConversionService.buildDateTimeString(date = timestamp)
        transaction.externalId = transactionId
        transaction.reservationId = reservationId
        return saveEntity(model = transaction, currentUser = currentUser)
    }

    /**
     * Update transaction entity.
     *
     * @param transactionId .
     * @param timestamp .
     * @param reason .
     * @param status .
     * @param currentUser .
     * @return Updated entity.
     */
    fun updateTransaction(
        transactionId: Int,
        timestamp: ZonedDateTime,
        reason: Reason,
        status: TransactionStatus,
        currentUser: CurrentUser
    ): TransactionEntity {
        logger.info("Updating transaction ID '$transactionId'")
        val optional = repositoryService.findByExternalId(externalId = transactionId)
        if (!optional.isPresent)
            throw Exception("Transaction with external ID '$transactionId' not found")

        val transaction = optional.get()
        transaction.dateTimeStopped = dateConversionService.buildDateTimeString(date = timestamp)
        transaction.reasonToStop = reason
        transaction.status = TransactionStatus.Finished
        return repositoryService.save(entity = transaction, currentUser = currentUser)
    }

    /**
     * Close all ongoing transactions. Optional a single connector can be defined for which the transactions shall be
     * closed.
     *
     * @param connectorId If null, all ongoing transactions are closed. If not, only ongoing transactions of the given
     * connector are closed.
     */
    fun closeAllOngoingTransactions(connectorId: Int? = null) {
        logger.info("Closing all ongoing transaction")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        repositoryService
            .findByStatus(status = TransactionStatus.Ongoing)
            .filter { if (connectorId == null) true else it.connector.externalId == connectorId }
            .toList()
            .forEach {
                logger.info("Setting status of transaction ID '${it.id}' to '${TransactionStatus.Finished}'")
                it.status = TransactionStatus.Finished
                repositoryService.save(entity = it, currentUser = currentUser)
            }
    }

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
