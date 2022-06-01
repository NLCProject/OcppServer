package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.image.ImageModelService
import org.ocpp.server.entities.image.ImageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SmartHomeModelService @Autowired constructor(
    filterService: SmartHomeFilterService,
    private val repositoryService: SmartHomeRepository,
    private val imageRepoService: ImageRepository,
    private val imageModelService: ImageModelService
) : ModelService<SmartHomeModel, SmartHomeEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = SmartHomeModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(
        entity: SmartHomeEntity,
        model: SmartHomeModel,
        currentUser: CurrentUser
    ) {
        model.image = imageModelService.findById(id = entity.imageId, currentUser = currentUser)
    }

    override fun createAbstractModel(entity: SmartHomeEntity, model: NamedModel, currentUser: CurrentUser) {
        model.thumbnail = imageRepoService.findThumbnail(id = entity.imageId, currentUser = currentUser)
    }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedBy { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
