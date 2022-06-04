package test.server.services

import eu.chargetime.ocpp.model.core.AvailabilityType
import org.isc.utils.tests.CurrentUserFactory
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.services.requests.ChangeAvailabilityService
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ChangeAvailabilityServiceTest {

    @Autowired
    private lateinit var service: ChangeAvailabilityService

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Test
    fun changeAvailability() {
        val currentUser = CurrentUserFactory.getCurrentUser()
        val connector = testHelperService.createConnector(currentUser = currentUser)
        var transaction1 = testHelperService.createTransaction(
            connector = connector,
            status = TransactionStatus.Ongoing,
            currentUser = currentUser
        )

        var transaction2 = testHelperService.createTransaction(
            connector = connector,
            status = TransactionStatus.Finished,
            currentUser = currentUser
        )

        service.changeAvailability(connectorId = connector.externalId, type = AvailabilityType.values().random())
        transaction1 = transactionRepository.findById(id = transaction1.id, currentUser = currentUser)
        assertEquals(TransactionStatus.Finished, transaction1.status)

        transaction2 = transactionRepository.findById(id = transaction2.id, currentUser = currentUser)
        assertEquals(TransactionStatus.Finished, transaction2.status)
    }
}
