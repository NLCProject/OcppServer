package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel

/**
 *
 */
@GenerateTsModel
class ConnectorModel : IscModel() {

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var name: String = String()

    /**
     * Equal to ID tag.
     */
    @ValidateContent(type = ValidationType.NotZeroOrLower, required = true)
    var externalId: Int = 0

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var smartHomeId: String = String()
}
