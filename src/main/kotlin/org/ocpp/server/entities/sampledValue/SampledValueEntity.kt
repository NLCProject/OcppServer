package org.ocpp.server.entities.sampledValue

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.meterValue.MeterValueEntity
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "sampled_value")
class SampledValueEntity : IscEntity() {

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
    var valueData: String = String()

    /**
     *
     */
    @Column
    var contextData: String = String()

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var formatData: ValueFormat = ValueFormat.Raw

    /**
     *
     */
    @Column
    var measurand: String = String()

    /**
     *
     */
    @Column
    var phaseData: String = String()

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var location: Location = Location.Body

    /**
     *
     */
    @Column
    var unit: String = String()

    /**
     *
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "meterValue_id")
    lateinit var meterValue: MeterValueEntity
}