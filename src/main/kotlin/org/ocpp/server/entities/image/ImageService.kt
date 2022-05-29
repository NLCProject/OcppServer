package org.ocpp.server.entities.image

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.ImageModel
import org.ocpp.server.entities.image.interfaces.IImageDecoderService
import org.ocpp.server.enums.ImageSize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageService @Autowired constructor(
    repositoryService: ImageRepository,
    private val decoderService: IImageDecoderService
) : EntityService<ImageModel, ImageEntity>(
    entityClass = ImageEntity::class.java,
    repositoryService = repositoryService
) {

    override fun preSave(
        model: ImageModel,
        entity: ImageEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        entity.thumbnail = decoderService.decodeAndResize(base64String = model.base64String, size = ImageSize.THUMBNAIL)
        entity.base64String = decoderService.decodeAndResize(
            base64String = model.base64String,
            size = ImageSize.ORIGINAL
        )
    }

    override fun afterSave(
        model: ImageModel,
        entity: ImageEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: ImageEntity, currentUser: CurrentUser) { }

    override fun afterDelete(entity: ImageEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: ImageModel) { }
}
