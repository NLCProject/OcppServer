package org.ocpp.server.enums

import org.isc.utils.annotations.GenerateTsModel

/**
 * Notification view status.
 */
@GenerateTsModel
enum class NotificationViewStatus {

    /**
     * Notification is new and unseen.
     */
    New,

    /**
     * Notification is seen by an user.
     */
    Seen,

    /**
     * Notification is soft deleted.
     */
    Deleted
}
