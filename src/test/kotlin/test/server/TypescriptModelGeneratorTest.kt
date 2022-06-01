package test.server

import org.isc.utils.tests.TypescriptModelGenerator
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TypescriptModelGeneratorTest {

    @Autowired
    private lateinit var service: TypescriptModelGenerator

    @Test
    fun generate() {
        service.generate(
            packageName = "org/ocpp/server",
            subPackages = listOf("dtos", "enums"),
            pathToTypescriptModels = "./src/main/kotlin/org/ocpp/server/frontend/src/app/models"
        )
    }
}
