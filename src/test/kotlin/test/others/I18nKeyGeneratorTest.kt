package test.others

import org.isc.utils.tests.I18nKeyGenerator
import org.junit.jupiter.api.Test
import org.ocpp.server.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertFalse

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class I18nKeyGeneratorTest {

    @Autowired
    private lateinit var service: I18nKeyGenerator

    private val pathToAssets = "./src/main/kotlin/org/ocpp/server/frontend/src/assets/i18n"

    @Test
    fun generate() {
        service.generate(
            pathToAssets = pathToAssets,
            packageName = "oneMove",
            pathToSources = "./src/main/kotlin/org/ocpp/server/frontend/src/app",
            snackbarFunctions = listOf("showSnackbarOnError", "showSnackbar"),
            additionalI18nKeys = emptyList(),
            i18nKeysToIgnore = listOf("Cookie", "Yes", "No", "To", "true", "false"),
            topLevelFoldersToInclude = listOf("shared", "generics", "toolbar", "password-reset", "login", "translation")
        )

        assertFalse(service.hasDuplicatedKeys(pathToAssets = pathToAssets))
        assertFalse(service.hasInvalidKeys(pathToAssets = pathToAssets))
    }
}
