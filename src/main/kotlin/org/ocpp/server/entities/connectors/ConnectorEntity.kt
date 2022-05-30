package org.ocpp.server.entities.connectors

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.notification.NotificationEntity
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.transaction.TransactionEntity
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
    var connectorName: String = String()

    /**
     * Equal to ID tag.
     */
    @Column
    var externalId: Int = 0

    /**
     *
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "smartHome_id")
    lateinit var smartHome: SmartHomeEntity

    /**
     *
     */
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "connector")
    var notifications: List<NotificationEntity> = emptyList()

    /**
     *
     */
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "connector")
    var transactions: List<TransactionEntity> = emptyList()
}
