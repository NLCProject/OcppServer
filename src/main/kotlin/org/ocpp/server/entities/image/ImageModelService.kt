package org.ocpp.server.entities.image

import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.ocpp.server.dtos.ImageModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageModelService @Autowired constructor(
    repositoryService: ImageRepository,
    filterService: ImageFilterService
) : ModelService<ImageModel, ImageEntity>(
    repositoryService = repositoryService,
    filterService = filterService,
    modelClass = ImageModel::class.java,
    abstractClass = NamedModel::class.java
) {

    override fun createModel(entity: ImageEntity, model: ImageModel, currentUser: CurrentUser) { }

    override fun createAbstractModel(entity: ImageEntity, model: NamedModel, currentUser: CurrentUser) { }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        throw NotImplementedError()

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedByDescending { it.timestampCreated }
}
