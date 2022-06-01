package org.ocpp.server.enums

import org.isc.utils.annotations.GenerateTsModel

/**
 * Transaction status.
 */
@GenerateTsModel
enum class TransactionStatus {

    Ongoing,
    Finished
}
