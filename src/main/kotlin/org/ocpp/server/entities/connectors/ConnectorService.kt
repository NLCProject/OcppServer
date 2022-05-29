package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.models.Aspects
import org.isc.utils.genericCrudl.services.EntityService
import org.isc.utils.models.CurrentUser
import org.ocpp.server.dtos.ConnectorModel
import org.ocpp.server.entities.notification.NotificationService
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.transaction.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConnectorService @Autowired constructor(
    private val repositoryService: ConnectorRepository,
    private val smartHomeRepository: SmartHomeRepository,
    private val transactionService: TransactionService,
    private val notificationService: NotificationService
) : EntityService<ConnectorModel, ConnectorEntity>(
    entityClass = ConnectorEntity::class.java,
    repositoryService = repositoryService
) {

    /**
     *
     */
    fun findOrCreateConnector(externalId: Int, sessionIndex: String, currentUser: CurrentUser): ConnectorEntity {
        val optional = repositoryService.findByExternalId(externalId = externalId)
        if (optional.isPresent)
            return optional.get()

        val connector = ConnectorModel()
        connector.externalId = externalId
        connector.name = externalId.toString()
        connector.smartHomeId = smartHomeRepository.findBySessionIndex(sessionIndex = sessionIndex).get().id
        return saveEntity(model = connector, currentUser = currentUser)
    }

    override fun preSave(
        model: ConnectorModel,
        entity: ConnectorEntity,
        isPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) {
        if (!isPresent)
            entity.smartHome = smartHomeRepository.findById(id = model.smartHomeId, currentUser = currentUser)
    }

    override fun afterSave(
        model: ConnectorModel,
        entity: ConnectorEntity,
        wasPresent: Boolean,
        aspects: Aspects,
        currentUser: CurrentUser
    ) { }

    override fun preDelete(entity: ConnectorEntity, currentUser: CurrentUser) {
        entity.notifications.forEach { notificationService.deleteEntity(id = it.id, currentUser = currentUser) }
        entity.transactions.forEach { transactionService.deleteEntity(id = it.id, currentUser = currentUser) }
    }

    override fun afterDelete(entity: ConnectorEntity, currentUser: CurrentUser) { }

    override fun checkModelAndThrow(currentUser: CurrentUser, model: ConnectorModel) { }
}
