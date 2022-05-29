package org.ocpp.server.entities.transaction.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.transaction.TransactionEntity
import java.util.*

/**
 *
 */
interface ITransactionRepository : ICrudlRepository<TransactionEntity> {

    /**
     *
     */
    fun findByExternalId(externalId: Int): Optional<TransactionEntity>
}
