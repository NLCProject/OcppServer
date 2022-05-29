package org.ocpp.server.entities.image

import org.isc.utils.services.interfaces.IImageCodingService
import org.isc.utils.services.interfaces.IImageResizeService
import org.ocpp.server.enums.ImageSize
import org.ocpp.server.entities.image.interfaces.IImageDecoderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImageDecoderService @Autowired constructor(
    private val imageResizeService: IImageResizeService,
    private val imageCodingService: IImageCodingService
) : IImageDecoderService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun decodeAndResize(base64String: String?, size: ImageSize): String {
        logger.info("Decoding and resizing Base64 string")
        if (base64String == null || base64String.isEmpty())
            return String()

        var bufferedImage = imageCodingService.decodeToBufferedImage(base64String = base64String)
        if (bufferedImage.height > size.length || bufferedImage.width > size.length) {
            bufferedImage = imageResizeService.resize(
                bufferedImage = bufferedImage,
                height = size.length,
                width = size.length
            )
        }

        return imageCodingService.encodeBufferedImageToString(bufferedImage = bufferedImage)
    }
}
