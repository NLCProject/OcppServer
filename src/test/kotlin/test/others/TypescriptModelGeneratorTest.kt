package test.others

import eu.chargetime.ocpp.model.core.*
import org.battery.controller.util.controller.enums.AccessType
import org.battery.controller.util.controller.enums.DataType
import org.battery.controller.util.controller.modbusSimulator.ModbusCommand
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusRequest
import org.battery.controller.util.controller.modbusSimulator.commands.ModbusResponse
import org.battery.controller.util.controller.register.Register
import org.battery.controller.util.controller.register.descriptors.Descriptor
import org.battery.controller.util.controller.register.descriptors.enums.DescriptorType
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
                NotificationViewStatus::class.java,
                ChargePointErrorCode::class.java,
                ChargePointStatus::class.java,
                AvailabilityType::class.java,
                DescriptorType::class.java,
                ModbusResponse::class.java,
                ModbusRequest::class.java,
                ModbusCommand::class.java,
                ValueFormat::class.java,
                AccessType::class.java,
                Descriptor::class.java,
                ResetType::class.java,
                DataType::class.java,
                Register::class.java,
                Location::class.java,
                Reason::class.java
            )
        )
    }
}
