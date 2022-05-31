package org.ocpp.server.entities.image

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import javax.persistence.*

@Entity
@Table(name = "image")
class ImageEntity : IscEntity() {

    @Id
    @Column
    override var id: String = Ids.getRandomId()

    @Column
    override var organisationId: String = String()

    @Column
    override var timestampCreated: Long = System.currentTimeMillis()

    @Column
    override var timestampLastModified: Long = 0

    /**
     * Base 64 string of original image.
     */
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    var base64String: String? = null

    /**
     * Base 64 string of thumbnail.
     */
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    var thumbnail: String? = null
}
