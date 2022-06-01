package test.server

import org.isc.utils.tests.TestVerification
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class TestVerification {

    @Autowired
    private lateinit var service: TestVerification

    private val testPackage = "test"
    private val testFileSuffix = "Test"
    private val projectPackage = "org/ocpp/server"
    private val methodsToIgnore: List<String> = listOf(
        "getLogger", "init", "setLogger", "executeCronjob", "getFilterModels", "sortByDescending",
        "createAbstractModel", "createModel", "getRepositoryService", "afterDelete", "checkModelAndThrow",
        "getEntityService", "createSearchHintModel", "access"
    )

    @Test
    fun verifyTestClasses() {
        service.verifyTestClasses(
            testPackage = testPackage,
            testFileSuffix = testFileSuffix,
            projectPackage = projectPackage,
            methodsToIgnore = methodsToIgnore
        )
    }
}
