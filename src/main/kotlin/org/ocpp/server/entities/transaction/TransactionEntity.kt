package org.ocpp.server.entities.transaction

import eu.chargetime.ocpp.model.core.Reason
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType
import javax.persistence.*

@Entity
@Table(name = "transaction")
class TransactionEntity : IscEntity() {

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
     * Date when transaction is started.
     */
    @Column
    var dateTimeStarted: String = String()

    /**
     * Date when transaction is stopped.
     */
    @Column
    var dateTimeStopped: String = String()

    /**
     * External handled ID.
     */
    @Column
    var externalId: Int = 0

    /**
     * Current status.
     */
    @Column
    var status: TransactionStatus = TransactionStatus.Ongoing

    /**
     * Current type.
     */
    @Column
    var type: TransactionType = TransactionType.Outgoing
    // TODO Set type by the incoming data from the client.

    /**
     * Optional reservation ID. If 0, it is not reserved by a connector.
     */
    @Column
    var reservationId: Int = 0

    /**
     * Reason to stop.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var reasonToStop: Reason = Reason.Other

    /**
     * Connector parent.
     */
    @JoinColumn(name = "connector_id")
    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
    lateinit var connector: ConnectorEntity

    /**
     * Meter values of this transaction.
     */
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "transaction")
    var meterValues: List<MeterValueEntity> = emptyList()
}
