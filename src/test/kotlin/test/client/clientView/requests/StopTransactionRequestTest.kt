package test.client.clientView.requests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.server.request.StopTransactionRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.ITransactionStopService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StopTransactionRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var transactionStopService: ITransactionStopService

    @Test
    fun sendRequest() {
        val meterStop = Ids.getRandomId()
        val transactionId = Ids.getRandomId()
        val confirmation = clientRequestService.stopTransaction(meterStop = meterStop, transactionId = transactionId)
        assertNull(confirmation.idTagInfo)

        val argumentCaptor = argumentCaptor<StopTransactionRequestEvent>()
        verify(transactionStopService, times(1)).stopTransaction(argumentCaptor.capture())
        assertEquals(meterStop, argumentCaptor.firstValue.request.meterStop)
        assertEquals(transactionId, argumentCaptor.firstValue.request.transactionId)
    }

    @TestComponent
    class EventTestListener {
        @EventListener
        fun handle(event: StopTransactionRequestEvent) { }
    }
}
