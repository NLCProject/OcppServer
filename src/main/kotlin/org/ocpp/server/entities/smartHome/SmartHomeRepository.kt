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
     * Find smart home by its ID tag.
     *
     * @param idTag .
     * @return Optional of entity.
     */
    fun findByIdTag(idTag: String): Optional<SmartHomeEntity> = repository.findByIdTag(idTag = idTag)

    /**
     * Find smart home by its session index.
     *
     * @param sessionIndex .
     * @return Optional of entity.
     */
    fun findBySessionIndex(sessionIndex: String): Optional<SmartHomeEntity> =
        repository.findBySessionIndex(sessionIndex = sessionIndex)

    /**
     * Find smart home by session index or identifier.
     *
     * @param sessionIndex .
     * @param identifier .
     * @return Optional of entity.
     */
    fun findBySessionIndexOrIdentifier(sessionIndex: String, identifier: String): Optional<SmartHomeEntity> =
        repository.findBySessionIndexOrIdentifier(sessionIndex = sessionIndex, identifier = identifier)

    /**
     * Find all smart homes where the last heartbeat timestamp is in between the given timestamps.
     *
     * @param timestampFrom Older timestamp.
     * @param timestampTo Newer timestamp.
     * @param status Excluded status to search for.
     * @return List of entities.
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

    /**
     * Find all smart homes where the last heartbeat timestamp is older than the given timestamp.
     *
     * @param timestamp .
     * @param status Excluded status to search for.
     * @return List of entities.
     */
    fun findAllByLastHeartbeatTimestampLessThanAndStatusNot(
        timestamp: Long,
        status: SmartHomeStatus
    ): List<SmartHomeEntity> = repository.findAllByLastHeartbeatTimestampLessThanAndStatusNot(
        timestamp = timestamp,
        status = status
    )
}
