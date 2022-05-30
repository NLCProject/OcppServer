package test.client.clientView.requests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.server.request.HeartbeatRequestEvent
import org.ocpp.server.services.events.interfaces.IHeartbeatService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertNotNull

class HeartbeatRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var heartbeatService: IHeartbeatService

    @Test
    fun sendRequest() {
        val confirmation = clientRequestService.heartbeat()
        assertNotNull(confirmation.currentTime)
        verify(heartbeatService, times(1)).heartbeat(anyOrNull())
    }
}
