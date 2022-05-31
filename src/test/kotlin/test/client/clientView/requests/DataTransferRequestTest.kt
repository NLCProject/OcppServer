package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.DataTransferStatus
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.event.server.request.ServerDataTransferRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.IDataTransferService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DataTransferRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var dataTransferService: IDataTransferService

    @Test
    fun sendRequest() {
        val vendorId = Ids.getRandomIdString()
        val data = Ids.getRandomIdString()
        val confirmation = clientRequestService.dataTransfer(vendorId = vendorId, data = data)
        assertEquals(DataTransferStatus.Accepted, confirmation.status)
        assertNull(confirmation.data)

        val argumentCaptor = argumentCaptor<ServerDataTransferRequestEvent>()
        verify(dataTransferService, times(1)).handleDataTransfer(argumentCaptor.capture())
        assertEquals(vendorId, argumentCaptor.firstValue.request.vendorId)
        assertEquals(data, argumentCaptor.firstValue.request.data)
    }
}
