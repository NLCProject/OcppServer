package org.ocpp.server.enums

/**
 *
 */
enum class ImageSize(

    /**
     *
     */
    var length: Int
) {

    /**
     *
     */
    THUMBNAIL(length = 40),

    /**
     *
     */
    ORIGINAL(length = 2048)
}
