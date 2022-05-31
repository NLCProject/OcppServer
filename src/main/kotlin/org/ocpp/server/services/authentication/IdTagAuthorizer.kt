package org.ocpp.server.services.authentication

import org.ocpp.client.application.Organisation
import java.lang.Exception

/**
 * Verifies ID tags.
 */
object IdTagAuthorizer {

    /**
     * Verifies if the given tag matches the organisation's validation ID.
     *
     * @param idTag .
     * @throws Exception In case the IDs are not matching.
     */
    fun authorizeAndThrow(idTag: String) {
        if (Organisation.validationId != idTag)
            throw Exception("ID tag '$idTag' is NOT valid")
    }
}
