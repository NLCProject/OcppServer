package org.ocpp.server.entities.meterValue

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.meterValue.interfaces.IMeterValueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeterValueRepository @Autowired constructor(
    repository: IMeterValueRepository
) : RepositoryService<MeterValueEntity>(repository = repository)