package org.ocpp.server.services.authentication

import org.ocpp.client.application.Organisation
import java.lang.Exception

/**
 *
 */
object IdTagAuthorizer {

    /**
     *
     */
    fun authorizeAndThrow(idTag: String) {
        if (Organisation.validationId != idTag)
            throw Exception("ID tag '$idTag' is NOT valid")
    }
}
