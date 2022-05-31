package org.ocpp.server.enums

/**
 * Notification view status.
 */
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
