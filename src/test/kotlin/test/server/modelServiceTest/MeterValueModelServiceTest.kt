package test.server.modelServiceTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.models.NamedModel
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.tests.ModelServiceTest
import org.isc.utils.tests.util.DataComparatorUtil
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.ocpp.server.dtos.MeterValueModel
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueModelService
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class MeterValueModelServiceTest : ModelServiceTest<MeterValueEntity, MeterValueModel>() {

    @Autowired
    private lateinit var repositoryService: MeterValueRepository

    @Autowired
    private lateinit var modelService: MeterValueModelService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            repositoryService = repositoryService,
            modelService = modelService,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    @Test
    override fun findAllPageable_withFilter() {
        val entity1 = createEntity(currentUser = currentUser)
        val entity2 = createEntity(currentUser = currentUser)

        val models = modelService.findAllPageable(filter = FilterParameters(), page = 0, currentUser = currentUser)
        assertEquals(2, models.size)
        assertTrue(models.any { it.id == entity1.id })
        assertTrue(models.any { it.id == entity2.id })
    }

    @Test
    override fun checkAbstractIcons() { }

    override fun compareEntityAndAbstractModelAndThrow(entity: MeterValueEntity, model: NamedModel) {
        assertFalse(model.firstLine.translate)
        assertFalse(model.secondLine.translate)
        assertFalse(model.thirdLine.translate)
        assertTrue(model.secondLine.text.isEmpty())
        assertTrue(model.firstLine.text.isEmpty())
        assertTrue(model.thirdLine.text.isEmpty())
        assertNull(model.thumbnail)
        assertNull(model.data)
    }

    override fun compareEntityAndModelAndThrow(entity: MeterValueEntity, model: MeterValueModel) {
        DataComparatorUtil.compareEntityAndModelAndThrow(entity = entity, model = model)
    }

    override fun createEntity(currentUser: CurrentUser): MeterValueEntity =
        testHelperService.createMeterValue(currentUser = currentUser)
}
