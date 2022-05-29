package org.ocpp.server.controllers

import org.isc.utils.enums.Feature
import org.isc.utils.enums.Role
import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.Headers
import org.isc.utils.genericCrudl.services.exceptionHandler.interfaces.IExceptionHandler
import org.ocpp.server.dtos.ImageModel
import org.ocpp.server.entities.image.ImageModelService
import org.ocpp.server.entities.image.ImageService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@Controller
@RequestMapping(path = ["image"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class ImageController @Autowired constructor(
    private val modelService: ImageModelService,
    private val entityService: ImageService,
    private val userAuthenticationService: IAuthenticationService,
    private val exceptionHandler: IExceptionHandler
) {

    /**
     *
     */
    @PostMapping(value = ["/upload"])
    fun upload(
        @RequestBody model: ImageModel,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            val currentUser = userAuthenticationService.isPermitted(
                userId = userId,
                token = token,
                targetRoles = listOf(Role.General),
                targetFeature = Feature.General
            )

            entityService.saveEntity(model = model, currentUser = currentUser)
        }

    /**
     *
     */
    @PostMapping(value = ["/delete"])
    fun delete(
        @RequestParam id: String,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeAnyOperation {
            val currentUser = userAuthenticationService.isPermitted(
                userId = userId,
                token = token,
                targetRoles = listOf(Role.General),
                targetFeature = Feature.General
            )

            entityService.deleteEntity(id = id, currentUser = currentUser)
        }

    /**
     *
     */
    @GetMapping(value = ["/findById"])
    fun findById(
        @RequestParam imageId: String,
        @RequestHeader(Headers.ID) userId: String,
        @RequestHeader(Headers.Authorization) token: String
    ): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            val currentUser = userAuthenticationService.isPermitted(
                userId = userId,
                token = token,
                targetRoles = listOf(Role.General),
                targetFeature = Feature.General
            )

            modelService.findById(id = imageId, currentUser = currentUser)
        }
}
