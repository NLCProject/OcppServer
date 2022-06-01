package test.server.entityServiceTest.sampledValue

import eu.chargetime.ocpp.model.core.Location
import eu.chargetime.ocpp.model.core.ValueFormat
import org.isc.utils.models.CurrentUser
import org.isc.utils.tests.EntityServiceTest
import org.isc.utils.utils.Ids
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.SampledValueModel
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import org.ocpp.server.entities.sampledValue.SampledValueModelService
import org.ocpp.server.entities.sampledValue.SampledValueRepository
import org.ocpp.server.entities.sampledValue.SampledValueService
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import test.server.modelServiceTest.SampledValueModelServiceTest
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class SampledValueServiceTest : EntityServiceTest<SampledValueEntity, SampledValueModel>() {

    @Autowired
    private lateinit var repositoryService: SampledValueRepository

    @Autowired
    private lateinit var entityService: SampledValueService

    @Autowired
    private lateinit var modelService: SampledValueModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @Autowired
    private lateinit var meterValueRepository: MeterValueRepository

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
        val meterValue = testHelperService.createMeterValue(currentUser = currentUser)
        val sampledValue = testHelperService.createSampledValue(meterValue = meterValue, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(sampledValue)
        entityService.deleteEntity(id = sampledValue.id, currentUser = currentUser)

        val meterValueOptional = meterValueRepository.findByIdOptional(id = meterValue.id)
        assertTrue(meterValueOptional.isPresent)
    }

    @Test
    override fun saveEntityTest() {
        val meterValue = testHelperService.createMeterValue(currentUser = currentUser)
        val model = SampledValueModel()
        model.meterValueId = meterValue.id
        model.valueData = Ids.getRandomId()
        model.contextData = Ids.getRandomId()
        model.measurand = Ids.getRandomId()
        model.phaseData = Ids.getRandomId()
        model.unit = Ids.getRandomId()
        model.formatData = ValueFormat.values().random()
        model.location = Location.values().random()
        model.organisationId = currentUser.organisationId

        val sampledValue = entityService.saveEntity(model = model, currentUser = currentUser)
        entityManager.flush()
        entityManager.refresh(sampledValue)

        val meterValueOptional = meterValueRepository.findByIdOptional(id = meterValue.id)
        assertTrue(meterValueOptional.isPresent)

        model.id = sampledValue.id
        compareEntityAndModelAndThrow(model = model, entity = sampledValue, currentUser = currentUser, auto = false)
    }

    override fun preDelete(currentUser: CurrentUser, entity: SampledValueEntity) { }

    override fun preSave(currentUser: CurrentUser) { }

    override fun afterSave(currentUser: CurrentUser, entity: SampledValueEntity) { }

    override fun compareEntityAndModelAndThrow(
        entity: SampledValueEntity,
        model: SampledValueModel,
        auto: Boolean,
        currentUser: CurrentUser
    ) {
        SampledValueModelServiceTest().compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): SampledValueEntity =
        testHelperService.createSampledValue(currentUser = currentUser)

    override fun createModel(currentUser: CurrentUser): SampledValueModel {
        val sampledValue = createEntity(currentUser = currentUser)
        return modelService.findById(id = sampledValue.id, currentUser = currentUser)
    }
}
