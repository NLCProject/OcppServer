package org.ocpp.server.services.test

import org.isc.utils.services.interfaces.IImageCodingService
import org.isc.utils.utils.ResourceLoaderUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

/**
 * Used only for testing purpose to create test base 64 strings.
 */
@Service
class ImageTestHelperService @Autowired constructor(
    private val imageCodingService: IImageCodingService
) {

    fun loadImageAsString(path: String): String {
        val resource = ResourceLoaderUtil.getByteArrayFromResource(path = path)
        val bufferedImage = ByteArrayInputStream(resource).use { stream -> ImageIO.read(stream) }
        return imageCodingService.encodeBufferedImageToString(bufferedImage = bufferedImage)
    }
}
