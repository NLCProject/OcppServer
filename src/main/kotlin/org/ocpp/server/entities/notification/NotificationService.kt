package org.ocpp.server.entities.notification

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.NotificationModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.enums.NotificationViewStatus
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationService @Autowired constructor(
    private val repositoryService: NotificationRepository,
    private val connectorRepository: ConnectorRepository
) : EntityService<NotificationModel, NotificationEntity>(
    entityClass = NotificationEntity::class.java,
    repositoryService = repositoryService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Change view status of an entity.
     *
     * @param notificationId .
     * @param viewStatus .
     * @param currentUser .
     */
    fun changeViewStatus(notificationId: String, viewStatus: NotificationViewStatus, currentUser: CurrentUser) {
        logger.info("Changing view status to '${viewStatus.name}' for notification ID '$notificationId'")
        val notification = repositoryService.findById(id = notificationId, currentUser = currentUser)
        notification.viewStatus = viewStatus
        repositoryService.save(entity = notification, currentUser = currentUser)
    }

    override fun preSave(
        model: NotificationModel,
        entity: NotificationEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.connector = connectorRepository.findById(id = model.connectorId, currentUser = currentUser)
    }

    override fun afterSave(
        model: NotificationModel,
        entity: NotificationEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: NotificationEntity, currentUser: CurrentUser) { }

    override fun afterDelete(entity: NotificationEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: NotificationModel) { }
}
