package org.ocpp.server.services.authentication

import org.isc.utils.enums.Feature
import org.isc.utils.enums.Role
import org.isc.utils.enums.UserLanguage
import org.isc.utils.models.CurrentUser
import org.isc.utils.serialization.JsonSerialization
import org.isc.utils.utils.Ids
import org.ocpp.client.application.Organisation
import org.ocpp.server.services.authentication.interfaces.IAuthenticationService
import org.springframework.stereotype.Service

@Service
class AuthenticationService : IAuthenticationService, JsonSerialization() {

    override fun isPermitted(
        userId: String,
        token: String,
        targetRoles: List<Role>,
        targetFeature: Feature
    ): CurrentUser = isPermitted()

    override fun isPermitted(
        userId: String,
        token: String,
        targetRoles: List<Role>,
        targetFeatures: List<Feature>
    ): CurrentUser = isPermitted()

    override fun isPermitted() = CurrentUser(
        userId = Ids.getEmptyUUID(),
        fullName = Ids.getRandomId(),
        language = UserLanguage.DE,
        identifier = Ids.getRandomIdentifier(),
        organisationId = Organisation.id,
        organisationName = Organisation.name
    )
}
