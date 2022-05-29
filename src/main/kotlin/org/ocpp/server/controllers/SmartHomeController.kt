package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeFilterService
import org.ocpp.server.entities.smartHome.SmartHomeModelService
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
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
    userAuthenticationService: IAuthenticationService
) : GenericController<SmartHomeModel, SmartHomeEntity>(
    userAuthenticationService = userAuthenticationService,
    entityService = entityService,
    filterService = filterService,
    modelService = modelService,
    rolesSave = emptyList(),
    rolesRead = emptyList()
)
