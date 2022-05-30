package org.ocpp.server.entities.smartHome

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.enums.SmartHomeStatus
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "smart_home")
class SmartHomeEntity : IscEntity() {

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
     *
     */
    @Column
    var lastHeartbeatTimestamp: Long = System.currentTimeMillis()

    /**
     *
     */
    @Column
    var imageId: String = String()

    /**
     *
     */
    @Column
    var identifier: String = String()

    /**
     *
     */
    @Column
    var sessionIndex: String = String()

    /**
     *
     */
    @Column
    var authorized: Boolean = false

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var status: SmartHomeStatus = SmartHomeStatus.Online

    /**
     *
     */
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "smartHome")
    var connectors: List<ConnectorEntity> = emptyList()
}
