package org.ocpp.server.entities.transaction

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType
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
    @Column
    var externalId: Int = 0

    /**
     *
     */
    @Column
    var status: TransactionStatus = TransactionStatus.Ongoing

    /**
     *
     */
    @Column
    var type: TransactionType = TransactionType.Outgoing
    // TODO

    /**
     *
     */
    @Column
    var reservationId: Int = 0

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var reasonToStop: Reason = Reason.Other

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
