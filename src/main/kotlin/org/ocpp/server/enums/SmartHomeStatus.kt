package org.ocpp.server.enums

/**
 * Smart home status.
 */
enum class SmartHomeStatus {

    /**
     * Online and reachable. Heartbeats are received in time.
     */
    Online,

    /**
     * Previously online. Heartbeat messages not reveived for short time.
     */
    Standby,

    /**
     * Seems to be offline or not responding. Hearbeat messages not received for long time.
     */
    Offline
}
