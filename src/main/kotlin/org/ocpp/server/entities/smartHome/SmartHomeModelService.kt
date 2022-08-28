package org.ocpp.server.entities.smartHome

import org.isc.utils.enums.I18nKey
import org.isc.utils.enums.IconEnum
import org.isc.utils.genericCrudl.services.ModelService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.utils.StringUtil
import org.ocpp.server.dtos.SmartHomeModel
import org.ocpp.server.entities.image.ImageModelService
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.modbus.SmartHomeCommandCache
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

        if (entity.idTag.isNotEmpty())
            model.availableCommands = SmartHomeCommandCache.getCommands(idTag = entity.idTag)
    }

    override fun createAbstractModel(entity: SmartHomeEntity, model: NamedModel, currentUser: CurrentUser) {
        model.firstLine.text = StringUtil.joinWithSeparatorWithSpace(separator = "|", entity.name, entity.idTag)
        model.secondLine.text = entity.status.name
        model.secondLine.translate = true
        model.thumbnail = imageRepoService.findThumbnail(id = entity.imageId, currentUser = currentUser)

        if (entity.status == SmartHomeStatus.Standby)
            model.addIcon(icon = IconEnum.ONGOING, i18nKey = I18nKey.StandBy)

        if (entity.status == SmartHomeStatus.Offline)
            model.addIcon(icon = IconEnum.CANCEL, i18nKey = I18nKey.Offline)
    }

    override fun findAllPageable(filter: FilterParameters, page: Int, currentUser: CurrentUser): List<NamedModel> =
        repositoryService
            .findAllPageable(page = page, currentUser = currentUser)
            .map { super.convertToAbstractModel(entity = it, currentUser = currentUser) }
            .sortedByDescending { it.firstLine.text }

    override fun sortByDescending(elements: List<NamedModel>): List<NamedModel> =
        elements.sortedBy { it.firstLine.text }
}
