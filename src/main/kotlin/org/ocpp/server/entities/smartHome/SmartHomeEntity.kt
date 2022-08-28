package org.ocpp.server.entities.smartHome

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.enums.SmartHomeStatus
import javax.persistence.*

@Entity
@Table(name = "smart_home")
class SmartHomeEntity : IscEntity() {

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
     * Smart home name.
     */
    @Column
    var name: String = String()

    /**
     * Timestamp when last heartbeat is received.
     */
    @Column
    var lastHeartbeatTimestamp: Long = System.currentTimeMillis()

    /**
     * ID of its image in the database.
     */
    @Column
    var imageId: String = String()

    /**
     * Identifier of the client.
     */
    @Column
    var identifier: String = String()

    /**
     * Session index of its current connection.
     */
    @Column
    var sessionIndex: String = String()

    /**
     * ID via which the clients sends requests to identify itself on this server.
     */
    @Column
    var idTag: String = String()

    /**
     * Boolean if smart home has been authorized.
     */
    @Column
    var authorized: Boolean = false

    /**
     * Current status.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var status: SmartHomeStatus = SmartHomeStatus.Online

    /**
     * Connectors of this smart home.
     */
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "smartHome")
    var connectors: List<ConnectorEntity> = emptyList()
}
