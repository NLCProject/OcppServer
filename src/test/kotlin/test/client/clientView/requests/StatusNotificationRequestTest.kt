package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.event.server.request.StatusNotificationRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.IStatusNotificationService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class StatusNotificationRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var statusNotificationService: IStatusNotificationService

    @Test
    fun sendRequest() {
        val connectorId = Ids.getRandomId()
        val errorCode = ChargePointErrorCode.values().random()
        val status = ChargePointStatus.values().random()
        val confirmation = clientRequestService.statusNotification(
            connectorId = connectorId,
            errorCode = errorCode,
            status = status
        )

        assertNotNull(confirmation)

        val argumentCaptor = argumentCaptor<StatusNotificationRequestEvent>()
        verify(statusNotificationService, times(1)).handleNotification(argumentCaptor.capture())
        assertEquals(connectorId, argumentCaptor.firstValue.request.connectorId)
        assertEquals(errorCode, argumentCaptor.firstValue.request.errorCode)
        assertEquals(status, argumentCaptor.firstValue.request.status)
    }
}
