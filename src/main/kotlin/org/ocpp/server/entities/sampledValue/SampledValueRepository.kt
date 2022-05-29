package org.ocpp.server.entities.sampledValue

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.sampledValue.interfaces.ISampledValueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SampledValueRepository @Autowired constructor(
    repository: ISampledValueRepository
) : RepositoryService<SampledValueEntity>(repository = repository)