package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueFilterService
import org.ocpp.server.entities.meterValue.MeterValueModelService
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import org.ocpp.server.entities.sampledValue.SampledValueFilterService
import org.ocpp.server.entities.sampledValue.SampledValueModelService
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeFilterService
import org.ocpp.server.entities.smartHome.SmartHomeModelService
import org.ocpp.server.entities.smartHome.SmartHomeService
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionFilterService
import org.ocpp.server.entities.transaction.TransactionModelService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 *
 */
@Controller
@RequestMapping(path = ["transaction"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class TransactionController @Autowired constructor(
    entityService: TransactionService,
    filterService: TransactionFilterService,
    modelService: TransactionModelService,
    userAuthenticationService: IAuthenticationService
) : GenericController<TransactionModel, TransactionEntity>(
    userAuthenticationService = userAuthenticationService,
    entityService = entityService,
    filterService = filterService,
    modelService = modelService,
    rolesSave = emptyList(),
    rolesRead = emptyList()
)
