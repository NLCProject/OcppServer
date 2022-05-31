package org.ocpp.server.entities.sampledValue

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.meterValue.MeterValueEntity
import javax.persistence.*

@Entity
@Table(name = "sampled_value")
class SampledValueEntity : IscEntity() {

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
     * Value.
     */
    @Column
    var valueData: String = String()

    /**
     * Context.
     */
    @Column
    var contextData: String = String()

    /**
     * Format.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var formatData: ValueFormat = ValueFormat.Raw

    /**
     * Measurand.
     */
    @Column
    var measurand: String = String()

    /**
     * Phase.
     */
    @Column
    var phaseData: String = String()

    /**
     * Location.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var location: Location = Location.Body

    /**
     * Unit.
     */
    @Column
    var unit: String = String()

    /**
     * Meter value parent.
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "meterValue_id")
    lateinit var meterValue: MeterValueEntity
}
