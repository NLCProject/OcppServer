package org.ocpp.server.entities.image.interfaces

import org.ocpp.server.enums.ImageSize

/**
 * Service is used to decode and resize base 64 strings.
 */
interface IImageDecoderService {

    /**
     * Decode and resize a base 64 string by the given image size.
     *
     * @param base64String If null, an empty String is returned.
     * @param size .
     * @return Base 64 string.
     */
    fun decodeAndResize(base64String: String?, size: ImageSize): String
}
