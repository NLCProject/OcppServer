package org.ocpp.server.services.modbus

import org.isc.utils.models.CurrentUser
import org.ocpp.server.services.modbus.interfaces.IModbusCommandSendService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ModbusCommandSendService: IModbusCommandSendService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendCommand(commandId: String, smartHomeId: String, currentUser: CurrentUser) {
        logger.info("Sending command ID '$commandId' to smart home with smart home ID '$smartHomeId'")

    }
}
