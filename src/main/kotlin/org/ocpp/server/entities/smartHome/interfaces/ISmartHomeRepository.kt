package org.ocpp.server.entities.smartHome.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.enums.SmartHomeStatus
import java.util.*

/**
 *
 */
interface ISmartHomeRepository : ICrudlRepository<SmartHomeEntity> {

    /**
     *
     */
    fun findBySessionIndex(sessionIndex: String): Optional<SmartHomeEntity>

    /**
     *
     */
    fun findBySessionIndexOrIdentifier(sessionIndex: String, identifier: String): Optional<SmartHomeEntity>

    /**
     *
     */
    fun findAllByLastHeartbeatTimestampBetweenAndStatusNot(
        timestampFrom: Long,
        timestampTo: Long,
        status: SmartHomeStatus
    ): List<SmartHomeEntity>

    /**
     *
     */
    fun findAllByLastHeartbeatTimestampLessThanAndStatusNot(
        timestamp: Long,
        status: SmartHomeStatus
    ): List<SmartHomeEntity>
}
