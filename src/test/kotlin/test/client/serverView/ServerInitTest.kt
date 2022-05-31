package test.client.serverView

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.ocpp.client.server.interfaces.IServerInitService
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ServerInitTest {

    @Autowired
    private lateinit var service: IServerInitService

    @AfterEach
    fun close() {
        service.close()
    }

    @Test
    fun init() {
        val ipAddress = "127.0.0.1"
        service.init(ipAddress = ipAddress)
    }
}
