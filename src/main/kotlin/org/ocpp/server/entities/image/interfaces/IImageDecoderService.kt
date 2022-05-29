package org.ocpp.server.entities.image.interfaces

import org.ocpp.server.enums.ImageSize

/**
 *
 */
interface IImageDecoderService {

    /**
     *
     */
    fun decodeAndResize(base64String: String?, size: ImageSize): String
}
