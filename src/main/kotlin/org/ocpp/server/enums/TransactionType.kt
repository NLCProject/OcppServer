package org.ocpp.server.enums

import org.isc.utils.annotations.GenerateTsModel

/**
 * Transaction type.
 */
@GenerateTsModel
enum class TransactionType {

    /**
     * Power is imported and bought from the power supplier.
     */
    Incoming,

    /**
     * Power is exported and sold to the power supplier.
     */
    Outgoing
}
