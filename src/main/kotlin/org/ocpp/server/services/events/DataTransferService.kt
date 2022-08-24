package org.ocpp.server.services.events

import org.battery.controller.util.controller.modbusSimulator.commands.CommandUUID
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusOnInit
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusResponse
import org.isc.utils.serialization.JsonSerialization
import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.ocpp.server.services.events.interfaces.IDataTransferService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DataTransferService : IDataTransferService, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleDataTransfer(event: ServerDataTransferRequestEvent) {
        logger.info("Handling data transfer for session index '${event.sessionIndex}'")
        parsingAndHandleData(data = event.request.data)
    }

    private fun parsingAndHandleData(data: String) {
        logger.info("Parsing MODBUS data | $data")
        when {
            data.contains(CommandUUID.modbusOnInit) -> handleOnInit(data = super.decode(data = data))
            data.contains(CommandUUID.modbusResponse) -> handleResponse(data = super.decode(data = data))
            data.contains(CommandUUID.modbusRequest) -> throw Exception("Requests not supported")
            else -> throw Exception("Unknown or not defined UUID in received data | $data")
        }
    }

    private fun handleOnInit(data: ModbusOnInit) {
        logger.info("Handling on init data")
        // TODO
    }

    private fun handleResponse(data: ModbusResponse) {
        logger.info("Handling response data")
        // TODO
    }
}
