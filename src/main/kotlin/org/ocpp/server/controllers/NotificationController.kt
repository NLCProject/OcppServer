package org.ocpp.server.controllers

import org.isc.utils.enums.Feature
import org.isc.utils.enums.Role
import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.isc.utils.genericCrudl.controller.Headers
import org.ocpp.server.dtos.NotificationModel
import org.ocpp.server.entities.notification.NotificationEntity
import org.ocpp.server.entities.notification.NotificationFilterService
import org.ocpp.server.entities.notification.NotificationModelService
import org.ocpp.server.entities.notification.NotificationService
import org.ocpp.server.enums.NotificationViewStatus
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@Controller
@RequestMapping(path = ["notification"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class NotificationController @Autowired constructor(
    filterService: NotificationFilterService,
    modelService: NotificationModelService,
    private val entityService: NotificationService,
    private val userAuthenticationService: IAuthenticationService
) : GenericController<NotificationModel, NotificationEntity>(
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
    @PostMapping(value = ["/changeViewStatus"])
    fun changeViewStatus(
        @RequestParam notificationId: String,
        @RequestParam viewStatus: NotificationViewStatus,
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

            entityService.changeViewStatus(
                notificationId = notificationId,
                viewStatus = viewStatus,
                currentUser = currentUser
            )
        }
}
