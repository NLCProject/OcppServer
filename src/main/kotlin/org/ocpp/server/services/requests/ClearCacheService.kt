package org.ocpp.server.services.requests

import org.ocpp.client.server.interfaces.IServerRequestService
import org.ocpp.server.services.requests.interfaces.IClearCacheService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClearCacheService @Autowired constructor(
    private val serverRequestService: IServerRequestService
) : IClearCacheService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun clearCache() {
        logger.info("Clearing cache")
        serverRequestService.clearCache()
    }
}
