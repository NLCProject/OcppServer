package org.ocpp.server.services.migration.interfaces

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

/**
 * This service imports test data when the application is ready.
 */
interface IMigrationService {

    /**
     * Start the migration process. Is executed automatically when the application is ready.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun startMigration()
}
