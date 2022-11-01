package org.ocpp.server.entities.image.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.image.ImageEntity
import org.springframework.data.jpa.repository.Query
import java.util.*

/**
 * Image repository.
 */
interface IImageRepository : ICrudlRepository<ImageEntity> {

    /**
     * Find thumbnail by its ID.
     *
     * @param id .
     * @return Byte array of thumbnail. May be null.
     */
    @Query(value = "SELECT thumbnail FROM image WHERE image.id = ?1", nativeQuery = true)
    fun findThumbnailById(id: String): Optional<ByteArray?>
}
