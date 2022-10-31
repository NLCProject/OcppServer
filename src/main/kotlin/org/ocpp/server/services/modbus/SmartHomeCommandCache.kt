package org.ocpp.server.services.modbus

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusResponse
import org.isc.utils.annotations.IscUtil
import org.ocpp.server.entities.command.dto.ModbusCacheObject
import org.slf4j.LoggerFactory

/**
 *
 */
@IscUtil
object SmartHomeCommandCache {

    private var commands: MutableMap<String, List<ModbusCacheObject>> = hashMapOf()
    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     *
     */
    fun updateCommand(smartHomeId: String, response: ModbusResponse) {
        logger.info("Registering commands for smart home ID '$smartHomeId'")
        this.commands[smartHomeId]?.forEach {
            if (it.id == response.command.id)
                it.value = response.value.descriptor.value
        }
    }

    /**
     *
     */
    fun registerCommands(smartHomeId: String, commands: List<ModbusCommand>) {
        logger.info("Registering commands for smart home ID '$smartHomeId'")
        this.commands[smartHomeId] = commands.map { buildCacheObject(command = it) }
    }

    /**
     *
     */
    fun getCommandById(smartHomeId: String, commandId: String): ModbusCacheObject =
        getCommands(smartHomeId = smartHomeId).first { it.id == commandId }

    /**
     *
     */
    fun getCommands(smartHomeId: String): List<ModbusCacheObject> = commands[smartHomeId] ?: emptyList()

    /**
     *
     */
    fun clearByIdTag(smartHomeId: String) {
        logger.info("Clearing commands for smart home ID '$smartHomeId'")
        commands.remove(smartHomeId)
    }

    private fun buildCacheObject(command: ModbusCommand): ModbusCacheObject {
        val cacheObject = ModbusCacheObject()
        cacheObject.id = command.id
        cacheObject.command = command
        cacheObject.i18nKey = command.i18nKey.name
        return cacheObject
    }
}
