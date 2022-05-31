package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueFilterService
import org.ocpp.server.entities.meterValue.MeterValueModelService
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * REST controller for meter values.
 */
@Controller
@RequestMapping(path = ["meter-value"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class MeterValueController @Autowired constructor(
    entityService: MeterValueService,
    filterService: MeterValueFilterService,
    modelService: MeterValueModelService,
    userAuthenticationService: IAuthenticationService
) : GenericController<MeterValueModel, MeterValueEntity>(
    userAuthenticationService = userAuthenticationService,
    entityService = entityService,
    filterService = filterService,
    modelService = modelService,
    rolesSave = emptyList(),
    rolesRead = emptyList()
)
