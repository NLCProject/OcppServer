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
     * Max size is 20 chars. Is defined by the client.
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var idTag: String = String()

    /**
     * Equal to ID tag.
     */
    @ValidateContent(type = ValidationType.NotZeroOrLower, required = true)
    var connectorId: Int = 0

    /**
     *
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var smartHomeId: String = String()
}
