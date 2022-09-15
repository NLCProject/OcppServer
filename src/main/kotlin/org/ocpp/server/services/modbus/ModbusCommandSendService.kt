package org.ocpp.server.services.modbus

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.models.CurrentUser
import org.ocpp.server.services.modbus.interfaces.IModbusCommandSendService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ModbusCommandSendService: IModbusCommandSendService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendCommand(command: ModbusCommand, sessionIndex: String, currentUser: CurrentUser) {
        logger.info("Sending command '${command.name}' to smart home with session index '$sessionIndex'")

    }
}
