package org.ocpp.server.services.modbus.interfaces

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.models.CurrentUser

/**
 *
 */
interface IModbusCommandSendService {

    /**
     *
     */
    fun sendCommand(commandId: String, smartHomeId: String, currentUser: CurrentUser)
}
