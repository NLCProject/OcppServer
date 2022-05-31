package test.client.clientView.requests

import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.StartTransactionRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.ITransactionStartService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
import test.client.clientView.requests.helper.IdTagVerification
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StartTransactionRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var transactionStartService: ITransactionStartService

    @Test
    fun sendRequest() {
        val idTag = Organisation.validationId
        val connectorId = Ids.getRandomId()
        val meterStart = Ids.getRandomId()
        val confirmation = clientRequestService.startTransaction(
            idTag = idTag,
            meterStart = meterStart,
            connectorId = connectorId
        )

        IdTagVerification.verifyIdTagInfo(idTagInfo = confirmation.idTagInfo)
        assertTrue(confirmation.transactionId in 100_000..999_999)

        val argumentCaptor = argumentCaptor<StartTransactionRequestEvent>()
        verify(transactionStartService, times(1)).startTransaction(argumentCaptor.capture())
        assertEquals(idTag, argumentCaptor.firstValue.request.idTag)
        assertEquals(meterStart, argumentCaptor.firstValue.request.meterStart)
        assertEquals(connectorId, argumentCaptor.firstValue.request.connectorId)
    }
}
