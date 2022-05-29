package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.image.ImageService
import org.ocpp.server.entities.image.interfaces.IImageCreatorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SmartHomeService @Autowired constructor(
    repositoryService: SmartHomeRepository,
    private val connectorService: ConnectorService,
    private val imageService: ImageService,
    private val imageCreatorService: IImageCreatorService
) : EntityService<SmartHomeModel, SmartHomeEntity>(
    entityClass = SmartHomeEntity::class.java,
    repositoryService = repositoryService
) {

    override fun preSave(
        model: SmartHomeModel,
        entity: SmartHomeEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.imageId = imageCreatorService.create(currentUser = currentUser)
    }

    override fun afterSave(
        model: SmartHomeModel,
        entity: SmartHomeEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: SmartHomeEntity, currentUser: CurrentUser) {
        imageService.deleteEntity(id = entity.imageId, currentUser = currentUser)
        entity.connectors.forEach { connectorService.deleteEntity(id = it.id, currentUser = currentUser) }
    }

    override fun afterDelete(entity: SmartHomeEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: SmartHomeModel) { }
}
