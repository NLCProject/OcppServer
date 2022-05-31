package test.client.clientView.requests

import org.junit.jupiter.api.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.services.events.interfaces.IHeartbeatService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
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
