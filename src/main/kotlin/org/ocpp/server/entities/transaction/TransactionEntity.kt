package org.ocpp.server.entities.transaction

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.meterValue.MeterValueEntity
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "transaction")
class TransactionEntity : IscEntity() {

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
    var dateTimeStarted: String = String()

    /**
     *
     */
    @Column
    var dateTimeStopped: String = String()

    /**
     *
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "connector_id")
    lateinit var connector: ConnectorEntity

    /**
     *
     */
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "transaction")
    var meterValues: List<MeterValueEntity> = emptyList()
}
