package test.client.serverView.requests

import eu.chargetime.ocpp.model.core.ResetStatus
import eu.chargetime.ocpp.model.core.ResetType
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.event.client.request.ResetRequestEvent
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import test.client.serverView.requests.helper.ServerRequestTest
import kotlin.test.assertEquals

class ResetRequestTest : ServerRequestTest() {

    @SpyBean
    private lateinit var eventListener: EventTestListener

    @Test
    fun sendRequest() {
        val type = ResetType.values().random()
        val confirmation = serverRequestService.reset(type = type)
        assertEquals(ResetStatus.Accepted, confirmation.status)

        val argumentCaptor = argumentCaptor<ResetRequestEvent>()
        verify(eventListener, times(1)).handle(argumentCaptor.capture())
        assertEquals(type, argumentCaptor.firstValue.request.type)
    }

    @TestComponent
    class EventTestListener {
        @EventListener
        fun handle(event: ResetRequestEvent) { }
    }
}
