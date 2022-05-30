package test.client.serverView.requests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.ocpp.server.Application
import org.ocpp.client.utils.Ids
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetConfigurationRequestTest : ServerRequestTest() {

    @Test
    fun sendRequest() {
        val keys = arrayOf(Ids.getRandomIdString(), Ids.getRandomIdString())
        val confirmation = serverRequestService.getConfiguration(keys = keys)
        assertEquals(1, confirmation.configurationKey.size)
        assertTrue(confirmation.configurationKey.first().readonly)
        assertEquals("TestKey", confirmation.configurationKey.first().key)
    }
}
