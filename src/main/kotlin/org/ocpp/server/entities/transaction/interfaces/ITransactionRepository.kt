package org.ocpp.server.entities.transaction.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.enums.TransactionStatus
import java.util.*

interface ITransactionRepository : ICrudlRepository<TransactionEntity> {

    /**
     * Find transaction by its external ID.
     *
     * @param externalId .
     * @return Optional of entity.
     */
    fun findByExternalId(externalId: Int): Optional<TransactionEntity>

    /**
     * Find all transactions by given status.
     *
     * @param status .
     * @return List of entities
     */
    fun findByStatus(status: TransactionStatus): List<TransactionEntity>
}
