package test.server

import org.isc.utils.tests.ControllerPathVerification
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class ControllerPathVerificationTest {

    @Autowired
    private lateinit var service: ControllerPathVerification

    private val projectPackage = "org/ocpp/server"

    @Test
    fun verifyPaths() {
        service.verifyPaths(projectPackage = projectPackage)
    }
}
