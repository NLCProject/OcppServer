package org.ocpp.server.configuration

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
class Configuration