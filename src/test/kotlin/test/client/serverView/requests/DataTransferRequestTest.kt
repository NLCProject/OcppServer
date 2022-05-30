package test.client.serverView.requests

import eu.chargetime.ocpp.model.core.DataTransferStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.client.request.ClientDataTransferRequestEvent
import org.ocpp.client.utils.Ids
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DataTransferRequestTest : ServerRequestTest() {

    @SpyBean
    private lateinit var eventListener: EventTestListener

    @Test
    fun sendRequest() {
        val vendorId = Ids.getRandomIdString()
        val data = Ids.getRandomIdString()
        val confirmation = serverRequestService.dataTransfer(vendorId = vendorId, data = data)
        assertEquals(DataTransferStatus.Accepted, confirmation.status)
        assertNull(confirmation.data)

        val argumentCaptor = argumentCaptor<ClientDataTransferRequestEvent>()
        verify(eventListener, times(1)).handle(argumentCaptor.capture())
        assertEquals(data, argumentCaptor.firstValue.request.data)
        assertEquals(vendorId, argumentCaptor.firstValue.request.vendorId)
    }

    @TestComponent
    class EventTestListener {
        @EventListener
        fun handle(event: ClientDataTransferRequestEvent) { }
    }
}
