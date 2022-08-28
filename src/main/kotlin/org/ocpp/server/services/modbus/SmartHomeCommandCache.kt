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
    fun registerCommands(idTag: String, commands: List<ModbusCommand>) {
        logger.info("Registering commands for ID tag '$idTag'")
        this.commands[idTag] = commands
    }

    /**
     *
     */
    fun getCommands(idTag: String): List<ModbusCommand> = commands[idTag]
        ?: throw Exception("No commands found for ID tag '$idTag'")

    /**
     *
     */
    fun clearByIdTag(idTag: String) {
        logger.info("Clearing commands for ID tag '$idTag'")
        commands.remove(idTag)
    }
}
