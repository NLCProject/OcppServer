package test.server.services

import eu.chargetime.ocpp.model.core.AvailabilityType
import org.isc.utils.tests.CurrentUserFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.mockito.Mock
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.client.utils.Ids
import org.ocpp.server.Application
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.services.requests.ChangeAvailabilityService
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ChangeAvailabilityServiceTest {

    private lateinit var service: ChangeAvailabilityService

    @Autowired
    private lateinit var transactionService: TransactionService

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Mock
    private lateinit var serverRequestService: IServerRequestService

    @BeforeEach
    fun setup() {
        service = ChangeAvailabilityService(
            transactionService = transactionService,
            serverRequestService = serverRequestService
        )
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(AvailabilityType::class)
    fun changeAvailability(type: AvailabilityType) {
        val currentUser = CurrentUserFactory.getCurrentUser()
        val connector1 = testHelperService.createConnector(currentUser = currentUser)
        val connector2 = testHelperService.createConnector(currentUser = currentUser)
        var transaction1 = testHelperService.createTransaction(
            connector = connector1,
            status = TransactionStatus.Ongoing,
            currentUser = currentUser
        )

        var transaction2 = testHelperService.createTransaction(
            connector = connector2,
            status = TransactionStatus.Finished,
            currentUser = currentUser
        )

        val sessionIndex = Ids.getRandomUUIDString()
        service.changeAvailability(connectorId = connector1.externalId, type = type, sessionIndex = Ids.getRandomUUIDString())
        transaction1 = transactionRepository.findById(id = transaction1.id, currentUser = currentUser)
        assertEquals(TransactionStatus.Finished, transaction1.status)

        transaction2 = transactionRepository.findById(id = transaction2.id, currentUser = currentUser)
        assertEquals(TransactionStatus.Finished, transaction2.status)

        verify(serverRequestService).changeAvailability(connectorId = eq(connector1.externalId), type = eq(type), sessionIndex = eq(sessionIndex))
    }
}
