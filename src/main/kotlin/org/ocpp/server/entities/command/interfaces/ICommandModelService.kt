package org.ocpp.server.entities.command.interfaces

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.models.NamedModel
import org.ocpp.server.entities.command.dto.ModbusCommandDto

/**
 *
 */
interface ICommandModelService {

    /**
     *
     */
    fun getById(smartHomeId: String, commandId: String): ModbusCommandDto

    /**
     *
     */
    fun convertToNamedModel(command: ModbusCommand): NamedModel
}
