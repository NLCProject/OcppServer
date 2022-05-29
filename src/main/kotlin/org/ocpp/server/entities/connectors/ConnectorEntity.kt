package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "connector")
class ConnectorEntity : IscEntity() {

    /**
     *
     */
    @Id
    @Column
    override var id: String = Ids.getRandomId()

    /**
     *
     */
    @Column
    override var organisationId: String = String()

    /**
     *
     */
    @Column
    override var timestampCreated: Long = System.currentTimeMillis()

    /**
     *
     */
    @Column
    override var timestampLastModified: Long = 0

    /**
     *
     */
    @Column
    var name: String = String()

    /**
     * Max size is 20 chars. Is defined by the client.
     */
    @Column
    var idTag: String = String()

    /**
     *
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "smartHome_id")
    lateinit var smartHome: SmartHomeEntity
}