package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
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
 * REST controller for images.
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
     * Upload an image model and update the base 64 strings in the database.
     *
     * @param model .
     */
    @PostMapping(value = ["/upload"])
    fun upload(@RequestBody model: ImageModel): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            val currentUser = userAuthenticationService.isPermitted()
            entityService.saveEntity(model = model, currentUser = currentUser)
        }

    /**
     * Delete an image by ID.
     *
     * @param id .
     */
    @PostMapping(value = ["/delete"])
    fun delete(@RequestParam id: String): ResponseEntity<*> =
        exceptionHandler.executeAnyOperation {
            val currentUser = userAuthenticationService.isPermitted()
            entityService.deleteEntity(id = id, currentUser = currentUser)
        }

    /**
     * Returns an image model by the given ID.
     *
     * @param id .
     * @return Image model.
     */
    @GetMapping(value = ["/findById"])
    fun findById(@RequestParam id: String): ResponseEntity<*> =
        exceptionHandler.executeGetOperation {
            val currentUser = userAuthenticationService.isPermitted()
            modelService.findById(id = id, currentUser = currentUser)
        }
}
