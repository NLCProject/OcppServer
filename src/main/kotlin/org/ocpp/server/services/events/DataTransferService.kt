package org.ocpp.server.services.events

import org.battery.controller.util.controller.modbusSimulator.commands.CommandUUID
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusOnInit
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusResponse
import org.battery.controller.util.controller.register.descriptors.value.ValueDescriptor
import org.isc.utils.serialization.JsonSerialization
import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.services.events.interfaces.IDataTransferService
import org.ocpp.server.services.modbus.SmartHomeCommandCache
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataTransferService @Autowired constructor(
    private val repository: SmartHomeRepository
): IDataTransferService, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleDataTransfer(event: ServerDataTransferRequestEvent) {
        logger.info("Handling data transfer for session index '${event.sessionIndex}'")
        parsingAndHandleData(data = event.request.data)
    }

    private fun parsingAndHandleData(data: String) {
        logger.info("Parsing MODBUS data | $data")
        when {
            data.contains(CommandUUID.modbusOnInit) -> handleOnInit(onInit = super.decode(data = data))
            data.contains(CommandUUID.modbusResponse) -> handleResponse(response = super.decode(data = data))
            data.contains(CommandUUID.modbusRequest) -> throw Exception("Requests not supported")
            else -> throw Exception("Unknown or not defined UUID in received data | $data")
        }
    }

    private fun handleOnInit(onInit: ModbusOnInit) {
        logger.info("Handling on init data for ID tag '${onInit.idTag}'")
        val smartHome = repository.findByIdTag(idTag = onInit.idTag)
        if (!smartHome.isPresent)
            return logger.warn("Smart home with ID tag '${onInit.idTag}' not found on init")

        SmartHomeCommandCache.registerCommands(smartHomeId = smartHome.get().id, commands = onInit.availableCommands)
    }

    private fun handleResponse(response: ModbusResponse) {
        logger.info("Handling response data | ${(response.value.descriptor as ValueDescriptor).value}")
        // TODO
    }
}
