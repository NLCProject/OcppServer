package org.ocpp.server.entities.image.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.image.ImageEntity
import org.springframework.data.jpa.repository.Query
import java.util.*

/**
 *
 */
interface IImageRepository : ICrudlRepository<ImageEntity> {

    /**
     *
     */
    @Query(value = "SELECT thumbnail FROM image WHERE image.id = ?1", nativeQuery = true)
    fun findThumbnailById(id: String): Optional<ByteArray?>

    /**
     *
     */
    fun findByThumbnailIsNull(): List<ImageEntity>

    /**
     *
     */
    @Query(value = "SELECT thumbnail FROM image WHERE image.id = ?1 AND image.organisationId = ?2", nativeQuery = true)
    fun findThumbnailByIdAndOrganisationId(id: String, organisationId: String): Optional<ByteArray?>
}
