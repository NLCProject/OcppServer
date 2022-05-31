package test.server.filterServiceTest

import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.tests.FilterServiceTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.ocpp.server.Application
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.meterValue.MeterValueFilterService
import org.ocpp.server.entities.meterValue.MeterValueRepository
import org.ocpp.server.services.test.TestHelperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class MeterValueFilterServiceTest : FilterServiceTest<MeterValueEntity>() {

    @Autowired
    private lateinit var repositoryService: MeterValueRepository

    @Autowired
    private lateinit var filterService: MeterValueFilterService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            filterService = filterService,
            repositoryService = repositoryService,
            instancesToCreate = 2,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    override fun createFilters(filters: FilterOptions) {
        assertTrue(filters.isEmpty())
    }

    override fun findAllHints(entities: List<MeterValueEntity>) {
        val models = filterService.findAllHints(filter = FilterParameters(), currentUser = currentUser)
        assertTrue(models.isEmpty())
    }

    override fun createEntity(currentUser: CurrentUser): MeterValueEntity =
        testHelperService.createMeterValue(currentUser = currentUser)
}
