package test.client.clientView

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.*
import org.ocpp.client.client.interfaces.IClientInitService
import org.ocpp.client.event.client.ClientConnectedEvent
import org.ocpp.client.event.client.ClientConnectionLostEvent
import org.ocpp.client.server.interfaces.IServerInitService
import org.ocpp.server.Application
import org.ocpp.server.services.events.interfaces.IClientRegisterService
import org.ocpp.server.services.events.interfaces.IClientUnregisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ClientInitTest {

    @Autowired
    private lateinit var serverInitService: IServerInitService

    @Autowired
    private lateinit var clientInitService: IClientInitService

    @SpyBean
    private lateinit var eventListener: EventTestListener

    @SpyBean
    private lateinit var clientRegisterService: IClientRegisterService

    @SpyBean
    private lateinit var clientUnregisterService: IClientUnregisterService

    private val ipAddress = "127.0.0.1"

    @AfterEach
    fun close() {
        serverInitService.close()
        clientInitService.disconnect()
    }

    @Test
    fun init() {
        serverInitService.init(ipAddress = ipAddress)
        clientInitService.init(ipAddress = ipAddress)
        Thread.sleep(1_000)

        verify(eventListener, times(1)).handleClientConnect(anyOrNull())
        verify(clientRegisterService, times(1)).onClientConnected(anyOrNull())
        verify(eventListener, never()).handleClose(anyOrNull())
        verify(clientUnregisterService, never()).onClientDisconnected(anyOrNull())
    }

    @Test
    fun init_thenDisconnect() {
        serverInitService.init(ipAddress = ipAddress)
        clientInitService.init(ipAddress = ipAddress)
        Thread.sleep(1_000)

        verify(eventListener, times(1)).handleClientConnect(anyOrNull())
        verify(clientRegisterService, times(1)).onClientConnected(anyOrNull())
        verify(eventListener, never()).handleClose(anyOrNull())
        verify(clientUnregisterService, never()).onClientDisconnected(anyOrNull())

        clientInitService.disconnect()
        Thread.sleep(1_000)

        verify(eventListener, times(1)).handleClose(anyOrNull())
        verify(clientUnregisterService, times(1)).onClientDisconnected(anyOrNull())
    }

    @Test
    fun init_thenServerCloses() {
        serverInitService.init(ipAddress = ipAddress)
        clientInitService.init(ipAddress = ipAddress)
        Thread.sleep(1_000)

        verify(eventListener, times(1)).handleClientConnect(anyOrNull())
        verify(clientRegisterService, times(1)).onClientConnected(anyOrNull())
        verify(eventListener, never()).handleClose(anyOrNull())
        verify(clientUnregisterService, never()).onClientDisconnected(anyOrNull())

        serverInitService.close()
        Thread.sleep(1_000)

        verify(eventListener, times(1)).handleClose(anyOrNull())
        verify(clientUnregisterService, times(1)).onClientDisconnected(anyOrNull())
    }

    @Test
    fun init_serverNotStarted() {
        clientInitService.init(ipAddress = ipAddress)
        Thread.sleep(1_000)

        verify(eventListener, never()).handleClientConnect(anyOrNull())
        verify(eventListener, times(1)).handleClose(anyOrNull())
    }

    @TestComponent
    class EventTestListener {
        @EventListener(ClientConnectionLostEvent::class)
        fun handleClose(event: ClientConnectionLostEvent) { }

        @EventListener(ClientConnectedEvent::class)
        fun handleClientConnect(event: ClientConnectedEvent) { }
    }
}
