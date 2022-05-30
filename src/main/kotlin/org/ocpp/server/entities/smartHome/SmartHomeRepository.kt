package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.smartHome.interfaces.ISmartHomeRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SmartHomeRepository @Autowired constructor(
    private val repository: ISmartHomeRepository
) : RepositoryService<SmartHomeEntity>(repository = repository) {

    /**
     *
     */
    fun findBySessionIndex(sessionIndex: String): Optional<SmartHomeEntity> =
        repository.findBySessionIndex(sessionIndex = sessionIndex)

    /**
     *
     */
    fun findBySessionIndexOrIdentifier(sessionIndex: String, identifier: String): Optional<SmartHomeEntity> =
        repository.findBySessionIndexOrIdentifier(sessionIndex = sessionIndex, identifier = identifier)

    /**
     *
     */
    fun findAllByLastHeartbeatTimestampLessThanAndStatusNot(
        timestamp: Long,
        status: SmartHomeStatus
    ): List<SmartHomeEntity> = repository.findAllByLastHeartbeatTimestampLessThanAndStatusNot(
        timestamp = timestamp,
        status = status
    )


    /**
     *
     */
    fun findAllByLastHeartbeatTimestampBetweenAndStatusNot(
        timestampFrom: Long,
        timestampTo: Long,
        status: SmartHomeStatus
    ): List<SmartHomeEntity> = repository.findAllByLastHeartbeatTimestampBetweenAndStatusNot(
        timestampFrom = timestampFrom,
        timestampTo = timestampTo,
        status = status
    )
}
