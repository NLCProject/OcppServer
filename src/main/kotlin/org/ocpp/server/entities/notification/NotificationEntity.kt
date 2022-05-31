package org.ocpp.server.entities.notification

import eu.chargetime.ocpp.model.core.ChargePointErrorCode
import eu.chargetime.ocpp.model.core.ChargePointStatus
import org.isc.utils.genericCrudl.models.IscEntity
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.enums.NotificationViewStatus
import javax.persistence.*

@Entity
@Table(name = "notification")
class NotificationEntity : IscEntity() {

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
     * Additional information.
     */
    @Column
    var info: String = String()

    /**
     * Date when the notification has been created at the client.
     */
    @Column
    var dateTimeCreated: String = String()

    /**
     * Vendor ID.
     */
    @Column
    var vendorId: String = String()

    /**
     * Vendor error code.
     */
    @Column
    var vendorErrorCode: String = String()

    /**
     * Current view status.
     */
    @Column
    var viewStatus: NotificationViewStatus = NotificationViewStatus.New

    /**
     * Current client status.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var status: ChargePointStatus = ChargePointStatus.Available

    /**
     * Error code.
     */
    @Column
    @Enumerated(value = EnumType.STRING)
    var errorCode: ChargePointErrorCode = ChargePointErrorCode.NoError

    /**
     * Connector parent.
     */
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "connector_id")
    lateinit var connector: ConnectorEntity
}
