package test.client.clientView.requests

import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.application.Organisation
import org.ocpp.server.Application
import org.ocpp.client.event.server.request.AuthorizeRequestEvent
import org.ocpp.server.services.events.interfaces.IAuthorizeService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class AuthorizeRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var authorizeService: IAuthorizeService

    @Test
    fun sendRequest() {
        val idTag = Organisation.validationId
        clientRequestService.authorize(idTag = idTag)

        val argumentCaptor = argumentCaptor<AuthorizeRequestEvent>()
        verify(authorizeService, times(1)).authorize(argumentCaptor.capture())
        assertEquals(idTag, argumentCaptor.firstValue.request.idTag)
    }
}
