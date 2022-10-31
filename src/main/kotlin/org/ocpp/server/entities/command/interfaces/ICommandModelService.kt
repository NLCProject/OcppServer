package org.ocpp.server.entities.command.interfaces

import org.isc.utils.models.NamedModel
import org.ocpp.server.entities.command.dto.ModbusCacheObject

/**
 *
 */
interface ICommandModelService {

    /**
     *
     */
    fun getById(smartHomeId: String, commandId: String): ModbusCacheObject

    /**
     *
     */
    fun convertToNamedModel(command: ModbusCacheObject): NamedModel
}
