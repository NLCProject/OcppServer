package org.ocpp.server.services.availability.interfaces

import org.springframework.transaction.annotation.Transactional

/**
 * Service finds and updates smart homes which turned in standby or offline mode.
 */
@Transactional
interface IAvailabilityService {

    /**
     * Finds all smart homes which are either now in standby or offline and updates their status (in case it has been
     * changed). It doesn't detect smart homes which turned online again.
     */
    fun checkForUnavailableSmartHomes()
}
