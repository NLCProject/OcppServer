package org.ocpp.server.controllers

import eu.chargetime.ocpp.model.core.AvailabilityType
import eu.chargetime.ocpp.model.core.ResetType
import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.isc.utils.genericCrudl.controller.Headers
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeFilterService
import org.ocpp.server.entities.smartHome.SmartHomeModelService
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.ocpp.server.services.requests.interfaces.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@Controller
@RequestMapping(path = ["smart-home"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class SmartHomeController @Autowired constructor(
    entityService: SmartHomeService,
    filterService: SmartHomeFilterService,
    modelService: SmartHomeModelService,
    private val resetService: IResetService,
    private val clearCacheService: IClearCacheService,
    private val unlockConnectorService: IUnlockConnectorService,
    private val changeAvailabilityService: IChangeAvailabilityService,
    private val remoteStartTransactionService: IRemoteStartTransactionService,
    private val remoteStopTransactionService: IRemoteStopTransactionService,
    private val userAuthenticationService: IAuthenticationService
) : GenericController<SmartHomeModel, SmartHomeEntity>(
    userAuthenticationService = userAuthenticationService,
    entityService = entityService,
    filterService = filterService,
    modelService = modelService,
    rolesSave = emptyList(),
    rolesRead = emptyList()
) {

    /**
     *
     */
    @PostMapping(value = ["/unlockConnector"])
    fun unlockConnector(
        @RequestParam connectorId: Int,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            unlockConnectorService.unlockConnector(connectorId = connectorId)
        }

    /**
     *
     */
    @PostMapping(value = ["/changeAvailability"])
    fun changeAvailability(
        @RequestParam connectorId: Int,
        @RequestParam type: AvailabilityType,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            changeAvailabilityService.changeAvailability(connectorId = connectorId, type = type)
        }

    /**
     *
     */
    @PostMapping(value = ["/clearCacheService"])
    fun clearCacheService(
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            clearCacheService.clearCache()
        }

    /**
     *
     */
    @PostMapping(value = ["/reset"])
    fun reset(
        @RequestParam type: ResetType,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            resetService.reset(type = type)
        }

    /**
     *
     */
    @PostMapping(value = ["/remoteStartTransaction"])
    fun remoteStartTransaction(
        @RequestParam connectorId: Int,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStartTransactionService.start(connectorId = connectorId)
        }

    /**
     *
     */
    @PostMapping(value = ["/remoteStopTransaction"])
    fun remoteStopTransaction(
        @RequestParam transactionId: Int,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStopTransactionService.stop(transactionId = transactionId)
        }
}
