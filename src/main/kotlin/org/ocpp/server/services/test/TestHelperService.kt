package org.ocpp.server.services.test

import eu.chargetime.ocpp.model.core.*
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.services.dateTime.interfaces.IDateConversionService
import org.isc.utils.utils.Ids
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.image.ImageEntity
import org.ocpp.server.entities.image.ImageRepository
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.entities.notification.NotificationEntity
import org.ocpp.server.entities.notification.NotificationRepository
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import org.ocpp.server.entities.sampledValue.SampledValueRepository
import org.ocpp.server.entities.smartHome.SmartHomeEntity
import org.ocpp.server.entities.smartHome.SmartHomeRepository
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.enums.NotificationViewStatus
import org.ocpp.server.enums.SmartHomeStatus
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random

/**
 * Used only for testing purpose to create test entities.
 */
@Service
class TestHelperService @Autowired constructor(
    private val sampledValueRepository: SampledValueRepository,
    private val smartHomeRepository: SmartHomeRepository,
    private val connectorRepository: ConnectorRepository,
    private val notificationRepository: NotificationRepository,
    private val meterValueRepository: MeterValueRepository,
    private val transactionRepository: TransactionRepository,
    private val imageRepository: ImageRepository,
    private val imageTestHelperService: ImageTestHelperService,
    private val dateConversionService: IDateConversionService
) {

    fun createSmartHome(currentUser: CurrentUser): SmartHomeEntity {
        val smartHome = SmartHomeEntity()
        smartHome.authorized = Random.nextBoolean()
        smartHome.identifier = Ids.getRandomId()
        smartHome.name = Ids.getRandomId()
        smartHome.sessionIndex = Ids.getRandomId()
        smartHome.status = SmartHomeStatus.values().random()
        smartHome.imageId = createImage(currentUser = currentUser).id
        smartHome.organisationId = currentUser.organisationId
        return smartHomeRepository.save(entity = smartHome, currentUser = currentUser)
    }

    fun createConnector(smartHome: SmartHomeEntity? = null, currentUser: CurrentUser): ConnectorEntity {
        val connector = ConnectorEntity()
        connector.smartHome = smartHome ?: createSmartHome(currentUser = currentUser)
        connector.connectorName = Ids.getRandomId()
        connector.externalId = Ids.getRandomIdentifier()
        connector.organisationId = currentUser.organisationId
        return connectorRepository.save(entity = connector, currentUser = currentUser)
    }

    fun createNotification(connector: ConnectorEntity? = null, currentUser: CurrentUser): NotificationEntity {
        val notification = NotificationEntity()
        notification.connector = connector ?: createConnector(currentUser = currentUser)
        notification.info = Ids.getRandomId()
        notification.dateTimeCreated = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        notification.vendorId = Ids.getRandomId()
        notification.vendorErrorCode = Ids.getRandomId()
        notification.viewStatus = NotificationViewStatus.values().random()
        notification.status = ChargePointStatus.values().random()
        notification.errorCode = ChargePointErrorCode.values().random()
        notification.organisationId = currentUser.organisationId
        return notificationRepository.save(entity = notification, currentUser = currentUser)
    }

    fun createTransaction(connector: ConnectorEntity? = null, currentUser: CurrentUser): TransactionEntity {
        val transaction = TransactionEntity()
        transaction.connector = connector ?: createConnector(currentUser = currentUser)
        transaction.dateTimeStarted = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        transaction.dateTimeStopped = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        transaction.organisationId = currentUser.organisationId
        transaction.externalId = Ids.getRandomIdentifier()
        transaction.reservationId = Ids.getRandomIdentifier()
        transaction.status = TransactionStatus.values().random()
        transaction.type = TransactionType.values().random()
        transaction.reasonToStop = Reason.values().random()
        return transactionRepository.save(entity = transaction, currentUser = currentUser)
    }

    fun createMeterValue(transaction: TransactionEntity? = null, currentUser: CurrentUser): MeterValueEntity {
        val meterValue = MeterValueEntity()
        meterValue.transaction = transaction ?: createTransaction(currentUser = currentUser)
        meterValue.dateTimeCreated = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        meterValue.organisationId = currentUser.organisationId
        return meterValueRepository.save(entity = meterValue, currentUser = currentUser)
    }

    fun createSampledValue(meterValue: MeterValueEntity? = null, currentUser: CurrentUser): SampledValueEntity {
        val sampledValue = SampledValueEntity()
        sampledValue.meterValue = meterValue ?: createMeterValue(currentUser = currentUser)
        sampledValue.valueData = Ids.getRandomId()
        sampledValue.contextData = Ids.getRandomId()
        sampledValue.measurand = Ids.getRandomId()
        sampledValue.phaseData = Ids.getRandomId()
        sampledValue.unit = Ids.getRandomId()
        sampledValue.formatData = ValueFormat.values().random()
        sampledValue.location = Location.values().random()
        sampledValue.organisationId = currentUser.organisationId
        return sampledValueRepository.save(entity = sampledValue, currentUser = currentUser)
    }


    fun createImage(currentUser: CurrentUser): ImageEntity {
        val image = ImageEntity()
        image.organisationId = currentUser.organisationId
        image.thumbnail = imageTestHelperService.loadImageAsString(path = "images/test.jpg")
        image.base64String = imageTestHelperService.loadImageAsString(path = "images/test.jpg")
        return imageRepository.save(entity = image, currentUser = currentUser)
    }
}