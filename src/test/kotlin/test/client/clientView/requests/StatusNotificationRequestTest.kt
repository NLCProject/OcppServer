package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.server.request.StatusNotificationRequestEvent
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.IStatusNotificationService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

class StatusNotificationRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var statusNotificationService: IStatusNotificationService

    @Test
    fun sendRequest() {
        val connectorId = Ids.getRandomId()
        val errorCode = ChargePointErrorCode.values().random()
        val status = ChargePointStatus.values().random()
        clientRequestService.statusNotification(connectorId = connectorId, errorCode = errorCode, status = status)

        val argumentCaptor = argumentCaptor<StatusNotificationRequestEvent>()
        verify(statusNotificationService, times(1)).handleNotification(argumentCaptor.capture())
        assertEquals(connectorId, argumentCaptor.firstValue.request.connectorId)
        assertEquals(errorCode, argumentCaptor.firstValue.request.errorCode)
        assertEquals(status, argumentCaptor.firstValue.request.status)
    }
}
