package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.RegistrationStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.server.Application
import org.ocpp.client.event.server.request.BootNotificationRequestEvent
import org.ocpp.client.utils.Heartbeat
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.IBootNotificationService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

class BootNotificationRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var bootNotificationService: IBootNotificationService

    @Test
    fun sendRequest() {
        val chargePointVendor = Ids.getRandomIdString()
        val chargePointModel = Ids.getRandomIdString()
        val confirmation = clientRequestService.bootNotification(
            chargePointVendor = chargePointVendor,
            chargePointModel = chargePointModel
        )

        assertEquals(RegistrationStatus.Accepted, confirmation.status)
        assertEquals(Heartbeat.heartbeatInterval, confirmation.interval)

        val argumentCaptor = argumentCaptor<BootNotificationRequestEvent>()
        verify(bootNotificationService, times(1)).handleNotification(argumentCaptor.capture())
        assertEquals(chargePointVendor, argumentCaptor.firstValue.request.chargePointVendor)
        assertEquals(chargePointModel, argumentCaptor.firstValue.request.chargePointModel)
    }
}
