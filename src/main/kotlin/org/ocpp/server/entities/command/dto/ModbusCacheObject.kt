package org.ocpp.server.entities.command.dto

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.annotations.GenerateTsModel

/**
 *
 */
@GenerateTsModel
class ModbusCacheObject {

    /**
     *
     */
    var id: String = String()

    /**
     *
     */
    var value: String = String()

    /**
     *
     */
    lateinit var command: ModbusCommand

    /**
     *
     */
    var i18nKey: String = String()
}
