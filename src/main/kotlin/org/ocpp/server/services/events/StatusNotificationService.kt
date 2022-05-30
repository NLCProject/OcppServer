package org.ocpp.server.services.events

import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.StatusNotificationRequestEvent
import org.ocpp.server.dtos.NotificationModel
import org.ocpp.server.entities.connectors.ConnectorService
import org.ocpp.server.entities.notification.NotificationService
import org.ocpp.server.services.events.interfaces.IStatusNotificationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatusNotificationService @Autowired constructor(
    private val connectorService: ConnectorService,
    private val notificationService: NotificationService,
    private val dateConversionService: IDateConversionService
) : IStatusNotificationService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleNotification(event: StatusNotificationRequestEvent) {
        logger.info("Handle status notification for session index '${event.sessionIndex}'")
        val currentUser = CurrentUserFactory.getCurrentUser(organisationId = Organisation.id)
        val connector = connectorService.findOrCreateConnector(
            externalId = event.request.connectorId,
            sessionIndex = event.sessionIndex.toString(),
            currentUser = currentUser
        )

        val notification = NotificationModel()
        notification.connectorId = connector.id
        notification.status = event.request.status
        notification.errorCode = event.request.errorCode
        notification.info = event.request.info
        notification.dateTimeCreated = dateConversionService.buildDateTimeString(date = event.request.timestamp)
        notification.vendorId = event.request.vendorId
        notification.vendorErrorCode = event.request.vendorErrorCode
        notificationService.saveEntity(model = notification, currentUser = currentUser)
    }
}
