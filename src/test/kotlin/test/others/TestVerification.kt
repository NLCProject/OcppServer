package test.others

import org.isc.utils.tests.testVerification.TestVerification
import org.junit.jupiter.api.Test

class TestVerification {

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
        TestVerification.verifyTestClasses(
            testPackage = testPackage,
            testFileSuffix = testFileSuffix,
            projectPackage = projectPackage,
            methodsToIgnore = methodsToIgnore
        )
    }
}
