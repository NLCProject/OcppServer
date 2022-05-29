package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.ocpp.server.dtos.ConnectorModel
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorFilterService
import org.ocpp.server.entities.connectors.ConnectorModelService
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@Controller
@RequestMapping(path = ["connector"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class ConnectorController @Autowired constructor(
    entityService: ConnectorService,
    filterService: ConnectorFilterService,
    modelService: ConnectorModelService,
    userAuthenticationService: IAuthenticationService
) : GenericController<ConnectorModel, ConnectorEntity>(
    userAuthenticationService = userAuthenticationService,
    entityService = entityService,
    filterService = filterService,
    modelService = modelService,
    rolesSave = emptyList(),
    rolesRead = emptyList()
)