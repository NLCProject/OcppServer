package org.ocpp.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["org.ocpp.server", "org.isc.utils", "org.ocpp.client"])
@SpringBootApplication(scanBasePackages = ["org.ocpp.server", "org.isc.utils", "org.ocpp.client"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
