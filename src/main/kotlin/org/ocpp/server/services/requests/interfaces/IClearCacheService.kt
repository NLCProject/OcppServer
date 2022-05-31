package org.ocpp.server.services.requests.interfaces

/**
 * Service sends event to clear cache of the client.
 */
interface IClearCacheService {

    /**
     * Send clear cache event.
     */
    fun clearCache()
}
