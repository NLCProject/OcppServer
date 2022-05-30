package org.ocpp.server.services.availability

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.availability.interfaces.IAvailabilityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AvailabilityService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository
) : IAvailabilityService {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val offsetStandby = 5_000
    private val offsetOffline = 30_000

    override fun checkForUnavailableSmartHomes() {
        logger.trace("Checking for unavailable smart homes")
        val timestamp = System.currentTimeMillis()
        val currentUser = CurrentUserFactory.getCurrentUser()
        handleNewStandbySmartHomes(timestamp = timestamp, currentUser = currentUser)
        handleNewOfflineSmartHomes(timestamp = timestamp, currentUser = currentUser)
    }

    private fun handleNewStandbySmartHomes(timestamp: Long, currentUser: CurrentUser) = smartHomeRepository
        .findAllByLastHeartbeatTimestampBetweenAndStatusNot(
            timestampFrom = timestamp - offsetOffline,
            timestampTo = timestamp - offsetStandby,
            status = SmartHomeStatus.Standby
        )
        .toList()
        .forEach {
            logger.warn("Changing smart home with ID '${it.id}' to status '${SmartHomeStatus.Standby}'")
            it.status = SmartHomeStatus.Standby
            smartHomeRepository.save(entity = it, currentUser = currentUser)
        }

    private fun handleNewOfflineSmartHomes(timestamp: Long, currentUser: CurrentUser) = smartHomeRepository
        .findAllByLastHeartbeatTimestampLessThanAndStatusNot(
            timestamp = timestamp - offsetOffline,
            status = SmartHomeStatus.Offline
        )
        .toList()
        .forEach {
            logger.warn("Changing smart home with ID '${it.id}' to status '${SmartHomeStatus.Offline}'")
            it.status = SmartHomeStatus.Offline
            smartHomeRepository.save(entity = it, currentUser = currentUser)
        }
}
