package test.client.serverView.requests.helper

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.ocpp.client.client.interfaces.IClientInitService
import org.ocpp.client.server.interfaces.IServerInitService
import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
abstract class ServerRequestTest {

    @Autowired
    protected lateinit var serverInitService: IServerInitService

    @Autowired
    protected lateinit var clientInitService: IClientInitService

    @Autowired
    protected lateinit var serverRequestService: IServerRequestService

    protected val ipAddress = "192.168.56.1"

    @BeforeEach
    fun start() {
        serverInitService.init(ipAddress = ipAddress, testMode = true)
        clientInitService.init(ipAddress = ipAddress)
        Thread.sleep(5_000)
    }

    @AfterEach
    fun close() {
        clientInitService.disconnect()
        serverInitService.close()
    }
}
