package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.StartTransactionConfirmation
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.ITransactionStopService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
import test.client.clientView.requests.helper.IdTagVerification
import kotlin.test.assertEquals

class StopTransactionRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var transactionStopService: ITransactionStopService

    @Test
    fun sendRequest() {
        val meterStop = Ids.getRandomId()
        val transactionId = startTransaction().transactionId
        val confirmation = clientRequestService.stopTransaction(meterStop = meterStop, transactionId = transactionId)
        IdTagVerification.verifyIdTagInfo(idTagInfo = confirmation.idTagInfo)

        val argumentCaptor = argumentCaptor<StopTransactionRequestEvent>()
        verify(transactionStopService, times(1)).stopTransaction(argumentCaptor.capture())
        assertEquals(meterStop, argumentCaptor.firstValue.request.meterStop)
        assertEquals(transactionId, argumentCaptor.firstValue.request.transactionId)
    }

    private fun startTransaction(): StartTransactionConfirmation {
        val idTag = Organisation.validationId
        val connectorId = Ids.getRandomId()
        val meterStart = Ids.getRandomId()
        return clientRequestService.startTransaction(
            idTag = idTag,
            meterStart = meterStart,
            connectorId = connectorId
        )
    }
}
