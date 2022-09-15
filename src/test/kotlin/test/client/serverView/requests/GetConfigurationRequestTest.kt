package test.client.serverView.requests

import org.junit.jupiter.api.Test
import org.ocpp.client.utils.Ids
import test.client.serverView.requests.helper.ServerRequestTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetConfigurationRequestTest : ServerRequestTest() {

    @Test
    fun sendRequest() {
        val keys = arrayOf(Ids.getRandomIdString(), Ids.getRandomIdString())
        val confirmation = serverRequestService.getConfiguration(keys = keys, sessionIndex = String())
        assertEquals(1, confirmation.configurationKey.size)
        assertTrue(confirmation.configurationKey.first().readonly)
        assertEquals("TestKey", confirmation.configurationKey.first().key)
    }
}
