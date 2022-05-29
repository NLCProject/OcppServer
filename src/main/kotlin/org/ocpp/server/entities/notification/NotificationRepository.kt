package org.ocpp.server.entities.notification

import org.isc.utils.genericCrudl.services.RepositoryService
import org.ocpp.server.entities.notification.interfaces.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationRepository @Autowired constructor(
    repository: NotificationRepository
) : RepositoryService<NotificationEntity>(repository = repository)
