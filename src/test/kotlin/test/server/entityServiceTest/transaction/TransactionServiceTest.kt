package test.server.entityServiceTest.transaction

import eu.chargetime.ocpp.model.core.Reason
import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.tests.EntityServiceTest
import org.isc.utils.utils.Ids
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.TransactionModel
import org.ocpp.server.entities.connectors.ConnectorRepository
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.entities.transaction.TransactionEntity
import org.ocpp.server.entities.transaction.TransactionModelService
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.entities.transaction.TransactionService
import org.ocpp.server.enums.TransactionStatus
import org.ocpp.server.enums.TransactionType
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.TransactionModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.test.assertFalse

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TransactionServiceTest : EntityServiceTest<TransactionEntity, TransactionModel>() {

    @Autowired
    private lateinit var repositoryService: TransactionRepository

    @Autowired
    private lateinit var entityService: TransactionService

    @Autowired
    private lateinit var modelService: TransactionModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var meterValueRepository: MeterValueRepository

    @Autowired
    private lateinit var connectorRepository: ConnectorRepository

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostConstruct
    fun init() {
        init(
            repositoryService = repositoryService,
            entityService = entityService,
            modelService = modelService,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    @Test
    override fun deleteEntityTest() {
        val connector = testHelperService.createConnector(currentUser = currentUser)
        val transaction = testHelperService.createTransaction(connector = connector, currentUser = currentUser)
        val meterValue = testHelperService.createMeterValue(transaction = transaction, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(transaction)
        entityService.deleteEntity(id = transaction.id, currentUser = currentUser)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertTrue(connectorOptional.isPresent)

        val meterValueOptional = meterValueRepository.findByIdOptional(id = meterValue.id)
        assertFalse(meterValueOptional.isPresent)

        val transactionOptional = transactionRepository.findByIdOptional(id = transaction.id)
        assertFalse(transactionOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val connector = testHelperService.createConnector(currentUser = currentUser)
        val model = TransactionModel()
        model.connectorId = connector.id
        model.dateTimeStarted = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        model.dateTimeStopped = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        model.externalId = Ids.getRandomIdentifier()
        model.reservationId = Ids.getRandomIdentifier()
        model.status = TransactionStatus.values().random()
        model.type = TransactionType.values().random()
        model.reasonToStop = Reason.values().random()
        model.organisationId = currentUser.organisationId

        val transaction = entityService.saveEntity(model = model, currentUser = currentUser)
        val meterValue = testHelperService.createMeterValue(transaction = transaction, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(transaction)

        val connectorOptional = connectorRepository.findByIdOptional(id = connector.id)
        assertTrue(connectorOptional.isPresent)

        val meterValueOptional = meterValueRepository.findByIdOptional(id = meterValue.id)
        assertTrue(meterValueOptional.isPresent)

        model.id = transaction.id
        compareEntityAndModelAndThrow(model = model, entity = transaction, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: TransactionEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: TransactionEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: TransactionEntity,
        model: TransactionModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        TransactionModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): TransactionEntity =
        testHelperService.createTransaction(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): TransactionModel {
        val transaction = createEntity(currentUser = currentUser)
        return modelService.findById(id = transaction.id, currentUser = currentUser)
    }
}
