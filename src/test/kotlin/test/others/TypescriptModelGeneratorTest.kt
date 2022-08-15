package test.others

import eu.chargetime.ocpp.model.core.*
import org.isc.utils.tests.TypescriptModelGenerator
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.enums.NotificationViewStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TypescriptModelGeneratorTest {

    @Autowired
    private lateinit var service: TypescriptModelGenerator

    @Test
    fun generate() {
        service.generate(
            packageName = "org.ocpp.server",
            subPackages = listOf("dtos", "enums"),
            pathToTypescriptModels = "./src/main/kotlin/org/ocpp/server/frontend/src/app/models",
            additionalClassesToPrint = listOf(
                AvailabilityType::class.java,
                ResetType::class.java,
                NotificationViewStatus::class.java,
                ChargePointStatus::class.java,
                ChargePointErrorCode::class.java,
                ValueFormat::class.java,
                Location::class.java,
                Reason::class.java
            )
        )
    }
}
