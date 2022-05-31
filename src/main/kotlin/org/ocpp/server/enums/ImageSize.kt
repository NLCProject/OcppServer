package org.ocpp.server.enums

/**
 * Defines the image length.
 */
enum class ImageSize(

    /**
     * Image length.
     */
    var length: Int
) {

    THUMBNAIL(length = 40),
    ORIGINAL(length = 2048)
}
