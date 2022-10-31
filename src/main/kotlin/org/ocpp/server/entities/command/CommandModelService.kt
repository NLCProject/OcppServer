package org.ocpp.server.entities.command

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.models.NamedModel
import org.ocpp.server.entities.command.dto.ModbusCommandDto
import org.ocpp.server.entities.command.interfaces.ICommandModelService
import org.ocpp.server.services.modbus.SmartHomeCommandCache
import org.springframework.stereotype.Service

@Service
class CommandModelService: ICommandModelService {

    override fun getById(smartHomeId: String, commandId: String): ModbusCommandDto {
        val command = SmartHomeCommandCache.getCommandById(smartHomeId = smartHomeId, commandId = commandId)
        return convertToModel(command = command)
    }

    override fun convertToNamedModel(command: ModbusCommand): NamedModel {
        val model = NamedModel()
        model.id = command.id
        model.firstLine.text = command.i18nKey.name
        model.firstLine.translate = true
        return model
    }

    private fun convertToModel(command: ModbusCommand): ModbusCommandDto {
        val model = ModbusCommandDto()
        model.id = command.id
        model.i18nKey = command.i18nKey.name
        return model
    }
}
