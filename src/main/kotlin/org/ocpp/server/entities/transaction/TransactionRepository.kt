package org.ocpp.server.entities.transaction

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.transaction.interfaces.ITransactionRepository
import org.ocpp.server.enums.TransactionStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionRepository @Autowired constructor(
    private val repository: ITransactionRepository
) : RepositoryService<TransactionEntity>(repository = repository) {

    /**
     *
     */
    fun findByExternalId(externalId: Int): Optional<TransactionEntity> =
        repository.findByExternalId(externalId = externalId)

    /**
     *
     */
    fun findByStatus(status: TransactionStatus): List<TransactionEntity> = repository.findByStatus(status = status)
}
