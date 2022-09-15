package test.client.serverView.requests

import eu.chargetime.ocpp.model.core.ClearCacheStatus
import org.junit.jupiter.api.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.event.client.request.ClearCacheRequestEvent
import org.ocpp.client.utils.Ids
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import test.client.serverView.requests.helper.ServerRequestTest
import kotlin.test.assertEquals

class ClearCacheRequestTest : ServerRequestTest() {

    @SpyBean
    private lateinit var eventListener: EventTestListener

    @Test
    fun sendRequest() {
        val confirmation = serverRequestService.clearCache(sessionIndex = String())
        assertEquals(ClearCacheStatus.Accepted, confirmation.status)
        verify(eventListener, times(1)).handle(anyOrNull())
    }

    @TestComponent
    class EventTestListener {

        @EventListener
        fun handle(event: ClearCacheRequestEvent) { }
    }
}
