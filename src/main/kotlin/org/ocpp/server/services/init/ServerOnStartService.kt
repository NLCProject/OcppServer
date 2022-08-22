package org.ocpp.server.services.init

import org.ocpp.client.server.interfaces.IServerInitService
import org.ocpp.server.services.init.interfaces.IServerOnStartService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ServerOnStartService @Autowired constructor(
    private val serverInitService: IServerInitService
): IServerOnStartService {

    @Value("\${ocpp.ip.address}")
    lateinit var ipAddress: String

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun startServer() {
        logger.info("Starting OCPP server on IP address '$ipAddress'")
        serverInitService.init(ipAddress = ipAddress)
    }
}
