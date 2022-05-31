package org.ocpp.server.entities.meterValue

import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import org.ocpp.server.entities.transaction.TransactionEntity
import javax.persistence.*

@Entity
@Table(name = "meter_value")
class MeterValueEntity : IscEntity() {

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
     * Date when the measure has been taken.
     */
    @Column
    var dateTimeCreated: String = String()

    /**
     * Transaction parent.
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "transaction_id")
    lateinit var transaction: TransactionEntity

    /**
     * Sampled values of this meter value.
     */
    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "meterValue")
    var sampledValues: List<SampledValueEntity> = emptyList()
}
