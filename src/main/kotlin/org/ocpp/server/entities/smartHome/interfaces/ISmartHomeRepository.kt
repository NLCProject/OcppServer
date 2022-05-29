package org.ocpp.server.entities.smartHome.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.smartHome.SmartHomeEntity
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
}