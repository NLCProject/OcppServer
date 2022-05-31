package org.ocpp.server.controllers

import org.isc.utils.genericCrudl.controller.CrossOriginData
import org.isc.utils.genericCrudl.controller.GenericController
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionFilterService
import org.ocpp.server.entities.transaction.TransactionModelService
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * REST controller for images.
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
