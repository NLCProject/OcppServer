package org.ocpp.server.services.migration

import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.CurrentUserFactory
import org.ocpp.client.application.Organisation
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.services.migration.data.Connector
import org.ocpp.server.services.migration.data.SmartHomes
import org.ocpp.server.services.migration.interfaces.IMigrationService
import org.ocpp.server.services.test.TestHelperService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MigrationService @Autowired constructor(
    private val smartHomeRepository: SmartHomeRepository,
    private val testHelperService: TestHelperService
) : IMigrationService {

    @Value("\${ocpp.migration.enabled}")
    var migrationEnabled: Boolean = false

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun startMigration() {
        if (!migrationEnabled)
            return logger.warn("Data migration disabled")

        logger.info("Starting data migration")
        val currentUser = CurrentUserFactory.getCurrentUser(
            organisationId = Organisation.id,
            organisationName = Organisation.name
        )

        importSmartHomes(currentUser = currentUser)
        importConnectors(currentUser = currentUser)
        logger.info("Data migration finished")
    }

    private fun importSmartHomes(currentUser: CurrentUser) {
        logger.info("Importing smart homes")
        SmartHomes.data.forEach {
            testHelperService.createSmartHome(
                id = it.id,
                name = it.name,
                authorized = true,
                status = SmartHomeStatus.Online,
                currentUser = currentUser
            )
        }
    }

    private fun importConnectors(currentUser: CurrentUser) {
        logger.info("Importing connectors")
        Connector.data.forEach {
            val smartHome = smartHomeRepository.findById(id = it.smartHomeId, currentUser = currentUser)
            testHelperService.createConnector(
                connectorName = it.name,
                smartHome = smartHome,
                currentUser = currentUser
            )
        }
    }
}
