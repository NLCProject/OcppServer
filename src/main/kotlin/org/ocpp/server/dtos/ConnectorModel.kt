package org.ocpp.server.dtos

import org.isc.utils.annotations.GenerateTsModel
import org.isc.utils.annotations.ValidateContent
import org.isc.utils.enums.ValidationType
import org.isc.utils.genericCrudl.models.IscModel

/**
 * Connector model.
 */
@GenerateTsModel
class ConnectorModel : IscModel() {

    /**
     * Name of connector.
     */
    @ValidateContent(type = ValidationType.StringNotEmpty, required = true)
    var connectorName: String = String()

    /**
     * External handled ID.
     */
    var externalId: Int = 0

    /**
     * Smart home parent ID.
     */
    var smartHomeId: String = String()
}
