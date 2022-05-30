package org.ocpp.server.services.requests.interfaces

import eu.chargetime.ocpp.model.core.ResetType

/**
 *
 */
interface IResetService {

    /**
     *
     */
    fun reset(type: ResetType)
}
