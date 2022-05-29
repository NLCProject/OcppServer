package org.ocpp.server.entities.transaction

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.transaction.interfaces.ITransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionRepository @Autowired constructor(
    private val repository: ITransactionRepository
) : RepositoryService<TransactionEntity>(repository = repository)