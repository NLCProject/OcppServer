package org.ocpp.server.entities.smartHome.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.enums.SmartHomeStatus
import java.util.*

interface ISmartHomeRepository : ICrudlRepository<SmartHomeEntity> {

    /**
     * Find smart home by its session index.
     *
     * @param sessionIndex .
     * @return Optional of entity.
     */
    fun findBySessionIndex(sessionIndex: String): Optional<SmartHomeEntity>

    /**
     * Find smart home by session index or identifier.
     *
     * @param sessionIndex .
     * @param identifier .
     * @return Optional of entity.
     */
    fun findBySessionIndexOrIdentifier(sessionIndex: String, identifier: String): Optional<SmartHomeEntity>

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
    ): List<SmartHomeEntity>

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
    ): List<SmartHomeEntity>
}
