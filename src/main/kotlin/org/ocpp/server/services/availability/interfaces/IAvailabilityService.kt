package org.ocpp.server.services.availability.interfaces

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.annotation.Transactional

/**
 *
 */
@Transactional
interface IAvailabilityService {

    /**
     *
     */
    @Scheduled(fixedRate = 5_000)
    fun checkForUnavailableSmartHomes()
}