package org.ocpp.server.entities.notification

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "notification")
class NotificationEntity : IscEntity() {

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
    var info: String = String()

    /**
     *
     */
    @Column
    var dateCreated: String = String()

    /**
     *
     */
    @Column
    var vendorId: String = String()

    /**
     *
     */
    @Column
    var vendorErrorCode: String = String()

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var status: ChargePointStatus = ChargePointStatus.Available

    /**
     *
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    /**
     *
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "connector_id")
    lateinit var connector: ConnectorEntity
}