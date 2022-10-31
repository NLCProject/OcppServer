package org.ocpp.server.services.modbus

import org.battery.controller.util.controller.modbusSimulator.commands.ModbusRequest
import org.isc.utils.models.CurrentUser
import org.isc.utils.serialization.JsonSerialization
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.services.modbus.interfaces.IModbusCommandSendService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModbusCommandSendService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository,
    private val serverRequestService: IServerRequestService
): IModbusCommandSendService, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendCommand(commandId: String, smartHomeId: String, currentUser: CurrentUser) {
        logger.info("Sending command ID '$commandId' to smart home with smart home ID '$smartHomeId'")
        val smartHome = smartHomeRepository.findById(id = smartHomeId, currentUser = currentUser)
        val cacheObject = SmartHomeCommandCache.getCommandById(smartHomeId = smartHomeId, commandId = commandId)
        val request = ModbusRequest(command = cacheObject.command, value = "") // Value to be implemented
        val data = super.encode(model = request)
        serverRequestService.dataTransfer(vendorId = "", sessionIndex = smartHome.sessionIndex, data = data)
        logger.info("Command ID '$commandId' sent to smart home ID '$smartHomeId'")
    }
}
