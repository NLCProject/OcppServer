package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.services.exceptionHandler.interfaces.IExceptionHandler
import org.ocpp.server.entities.command.interfaces.ICommandModelService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.ocpp.server.services.modbus.interfaces.IModbusCommandSendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * REST controller for Modbus commands.
 */
@Controller
@Transactional
@RequestMapping(path = ["/command"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class CommandController @Autowired constructor(
    private val userAuthenticationService: IAuthenticationService,
    private val modbusCommandSendService: IModbusCommandSendService,
    private val commandModelService: ICommandModelService,
    private val exceptionHandler: IExceptionHandler
) {

    /**
     * Get command by ID.
     *
     * @param smartHomeId .
     * @param commandId .
     * @return Modbus command.
     */
    @GetMapping(value = ["/getCommandById"])
    fun getCommandById(@RequestParam smartHomeId: String, @RequestParam commandId: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            commandModelService.getById(smartHomeId = smartHomeId, commandId = commandId)
        }

    /**
     * Run command.
     *
     * @param smartHomeId .
     * @param commandId .
     */
    @PostMapping(value = ["/runCommand"])
    fun runCommand(@RequestParam smartHomeId: String, @RequestParam commandId: String): ResponseEntity<*> =
        exceptionHandler.executeAnyOperation {
            val currentUser = userAuthenticationService.isPermitted()
            modbusCommandSendService.sendCommand(
                commandId = commandId,
                smartHomeId = smartHomeId,
                currentUser = currentUser
            )
        }
}
