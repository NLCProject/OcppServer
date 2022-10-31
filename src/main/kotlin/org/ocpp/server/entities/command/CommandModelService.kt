package org.ocpp.server.entities.command

import org.isc.utils.models.NamedModel
import org.ocpp.server.entities.command.dto.ModbusCacheObject
import org.ocpp.server.entities.command.interfaces.ICommandModelService
import org.ocpp.server.services.modbus.SmartHomeCommandCache
import org.springframework.stereotype.Service

@Service
class CommandModelService: ICommandModelService {

    override fun getById(smartHomeId: String, commandId: String): ModbusCacheObject = SmartHomeCommandCache
        .getCommandById(smartHomeId = smartHomeId, commandId = commandId)

    override fun convertToNamedModel(command: ModbusCacheObject): NamedModel {
        val model = NamedModel()
        model.id = command.id
        model.firstLine.text = command.i18nKey
        model.firstLine.translate = true
        model.secondLine.text = command.value
        return model
    }
}
