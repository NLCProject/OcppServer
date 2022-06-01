package test.server.entityServiceTest.meterValue

import org.isc.utils.models.CurrentUser
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.tests.EntityServiceTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueModelService
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.entities.meterValue.MeterValueService
import org.ocpp.server.entities.sampledValue.SampledValueRepository
import org.ocpp.server.entities.transaction.TransactionRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.MeterValueModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.test.assertFalse

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class MeterValueServiceTest : EntityServiceTest<MeterValueEntity, MeterValueModel>() {

    @Autowired
    private lateinit var repositoryService: MeterValueRepository

    @Autowired
    private lateinit var entityService: MeterValueService

    @Autowired
    private lateinit var modelService: MeterValueModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var sampledValueRepository: SampledValueRepository

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
        val transaction = testHelperService.createTransaction(currentUser = currentUser)
        val meterValue = testHelperService.createMeterValue(transaction = transaction, currentUser = currentUser)
        val sampledValue = testHelperService.createSampledValue(meterValue = meterValue, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(meterValue)
        entityService.deleteEntity(id = meterValue.id, currentUser = currentUser)

        val transactionOptional = transactionRepository.findByIdOptional(id = transaction.id)
        assertTrue(transactionOptional.isPresent)

        val sampledValueOptional = sampledValueRepository.findByIdOptional(id = sampledValue.id)
        assertFalse(sampledValueOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val transaction = testHelperService.createTransaction(currentUser = currentUser)
        val model = MeterValueModel()
        model.transactionId = transaction.id
        model.dateTimeCreated = dateConversionService.buildDateTimeString(date = DateTimeUtil.dateNow())
        model.organisationId = currentUser.organisationId

        val meterValue = entityService.saveEntity(model = model, currentUser = currentUser)
        val sampledValue = testHelperService.createSampledValue(meterValue = meterValue, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(meterValue)

        val transactionOptional = transactionRepository.findByIdOptional(id = transaction.id)
        assertTrue(transactionOptional.isPresent)

        val sampledValueOptional = sampledValueRepository.findByIdOptional(id = sampledValue.id)
        assertTrue(sampledValueOptional.isPresent)

        model.id = meterValue.id
        compareEntityAndModelAndThrow(model = model, entity = meterValue, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: MeterValueEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: MeterValueEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: MeterValueEntity,
        model: MeterValueModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        MeterValueModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): MeterValueEntity =
        testHelperService.createMeterValue(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): MeterValueModel {
        val connector = createEntity(currentUser = currentUser)
        return modelService.findById(id = connector.id, currentUser = currentUser)
    }
}
