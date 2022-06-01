package org.ocpp.server.enums

import org.isc.utils.annotations.GenerateTsModel

/**
 * Defines the image length.
 */
@GenerateTsModel
enum class ImageSize(

    /**
     * Image length.
     */
    var length: Int
) {

    THUMBNAIL(length = 40),
    ORIGINAL(length = 2048)
}
