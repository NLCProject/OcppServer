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
     * Unlock a connector.
     *
     * @param connectorId .
     */
    @PostMapping(value = ["/unlockConnector"])
    fun unlockConnector(@RequestParam connectorId: Int): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            unlockConnectorService.unlockConnector(connectorId = connectorId)
        }

    /**
     * Change the availability of a connector.
     *
     * @param connectorId .
     * @param type Type of availability.
     */
    @PostMapping(value = ["/changeAvailability"])
    fun changeAvailability(@RequestParam connectorId: Int, @RequestParam type: AvailabilityType): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            changeAvailabilityService.changeAvailability(connectorId = connectorId, type = type)
        }

    /**
     * Clear cache of the client.
     */
    @PostMapping(value = ["/clearCache"])
    fun clearCache(): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            clearCacheService.clearCache()
        }

    /**
     * Reset client.
     *
     * @param type Type of reset.
     */
    @PostMapping(value = ["/reset"])
    fun reset(@RequestParam type: ResetType): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            resetService.reset(type = type)
        }

    /**
     * Start transaction at the client remotely.
     *
     * @param connectorId Connector for which the transaction shall be started.
     */
    @PostMapping(value = ["/remoteStartTransaction"])
    fun remoteStartTransaction(@RequestParam connectorId: Int): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStartTransactionService.start(connectorId = connectorId)
        }

    /**
     * Stop transaction at the client remotely.
     *
     * @param transactionId Transaction to stop.
     */
    @PostMapping(value = ["/remoteStopTransaction"])
    fun remoteStopTransaction(@RequestParam transactionId: Int): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            userAuthenticationService.isPermitted()
            remoteStopTransactionService.stop(transactionId = transactionId)
        }
}
