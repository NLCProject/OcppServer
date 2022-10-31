package org.ocpp.server.services.modbus

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.annotations.IscUtil
import org.slf4j.LoggerFactory

/**
 *
 */
@IscUtil
object SmartHomeCommandCache {

    private var commands: MutableMap<String, List<ModbusCommand>> = hashMapOf()
    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     *
     */
    fun registerCommands(smartHomeId: String, commands: List<ModbusCommand>) {
        logger.info("Registering commands for smart home ID '$smartHomeId'")
        this.commands[smartHomeId] = commands
    }

    /**
     *
     */
    fun getCommandById(smartHomeId: String, commandId: String): ModbusCommand = getCommands(smartHomeId = smartHomeId)
        .first { it.id == commandId }

    /**
     *
     */
    fun getCommands(smartHomeId: String): List<ModbusCommand> = commands[smartHomeId] ?: emptyList()

    /**
     *
     */
    fun clearByIdTag(smartHomeId: String) {
        logger.info("Clearing commands for smart home ID '$smartHomeId'")
        commands.remove(smartHomeId)
    }
}
