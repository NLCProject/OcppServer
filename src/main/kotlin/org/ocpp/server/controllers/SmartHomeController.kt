package org.ocpp.server.controllers

import eu.chargetime.ocpp.model.core.AvailabilityType
import eu.chargetime.ocpp.model.core.ResetType
import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
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
 * REST controller for smart homes.
 */
@Controller
@RequestMapping(path = ["/smart-home"])
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
     * Unlock a connector.
     *
     * @param connectorId .
     * @param sessionIndex .
     */
    @PostMapping(value = ["/unlockConnector"])
    fun unlockConnector(@RequestParam connectorId: Int, @RequestParam sessionIndex: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            unlockConnectorService.unlockConnector(connectorId = connectorId, sessionIndex = sessionIndex)
        }

    /**
     * Change the availability of a connector.
     *
     * @param connectorId .
     * @param type Type of availability.
     * @param sessionIndex .
     */
    @PostMapping(value = ["/changeAvailability"])
    fun changeAvailability(
        @RequestParam connectorId: Int,
        @RequestParam type: AvailabilityType,
        @RequestParam sessionIndex: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            changeAvailabilityService.changeAvailability(
                connectorId = connectorId,
                type = type,
                sessionIndex = sessionIndex
            )
        }

    /**
     * Clear cache of the client.
     *
     * @param sessionIndex .
     */
    @PostMapping(value = ["/clearCache"])
    fun clearCache(@RequestParam sessionIndex: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            clearCacheService.clearCache(sessionIndex = sessionIndex)
        }

    /**
     * Reset client.
     *
     * @param type Type of reset.
     * @param sessionIndex .
     */
    @PostMapping(value = ["/reset"])
    fun reset(@RequestParam type: ResetType, @RequestParam sessionIndex: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            resetService.reset(type = type, sessionIndex = sessionIndex)
        }

    /**
     * Start transaction at the client remotely.
     *
     * @param connectorId Connector for which the transaction shall be started.
     * @param sessionIndex .
     */
    @PostMapping(value = ["/remoteStartTransaction"])
    fun remoteStartTransaction(@RequestParam connectorId: Int, @RequestParam sessionIndex: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStartTransactionService.start(connectorId = connectorId, sessionIndex = sessionIndex)
        }

    /**
     * Stop transaction at the client remotely.
     *
     * @param transactionId Transaction to stop.
     * @param sessionIndex .
     */
    @PostMapping(value = ["/remoteStopTransaction"])
    fun remoteStopTransaction(@RequestParam transactionId: Int, @RequestParam sessionIndex: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStopTransactionService.stop(transactionId = transactionId, sessionIndex = sessionIndex)
        }
}
