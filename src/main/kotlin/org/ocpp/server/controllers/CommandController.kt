package org.ocpp.server.controllers

import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.services.exceptionHandler.interfaces.IExceptionHandler
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.ocpp.server.services.modbus.interfaces.IModbusCommandSendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * REST controller for Modbus commands.
 */
@Controller
@RequestMapping(path = ["/command"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class CommandController @Autowired constructor(
    private val userAuthenticationService: IAuthenticationService,
    private val modbusCommandSendService: IModbusCommandSendService,
    private val exceptionHandler: IExceptionHandler
) {

    /**
     *
     */
    @PostMapping(value = ["/runCommand"])
    fun runCommand(@RequestParam sessionIndex: String, @RequestParam command: ModbusCommand): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            val currentUser = userAuthenticationService.isPermitted()
            modbusCommandSendService.sendCommand(
                command = command,
                sessionIndex = sessionIndex,
                currentUser = currentUser
            )
        }
}
