package test.client.serverView.requests

import eu.chargetime.ocpp.model.core.ClearCacheStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.client.request.ClearCacheRequestEvent
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

class ClearCacheRequestTest : ServerRequestTest() {

    @SpyBean
    private lateinit var eventListener: EventTestListener

    @Test
    fun sendRequest() {
        val confirmation = serverRequestService.clearCache()
        assertEquals(ClearCacheStatus.Accepted, confirmation.status)

        val argumentCaptor = argumentCaptor<ClearCacheRequestEvent>()
        verify(eventListener, times(1)).handle(argumentCaptor.capture())
    }

    @TestComponent
    class EventTestListener {
        @EventListener
        fun handle(event: ClearCacheRequestEvent) { }
    }
}
