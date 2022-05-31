package test.client.clientView.requests.helper

import eu.chargetime.ocpp.model.core.AuthorizationStatus
import eu.chargetime.ocpp.model.core.IdTagInfo
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

object IdTagVerification {

    fun verifyIdTagInfo(idTagInfo: IdTagInfo) {
        assertEquals(AuthorizationStatus.Accepted, idTagInfo.status)
        assertTrue(idTagInfo.parentIdTag.isEmpty())
        assertNotNull(idTagInfo.expiryDate)
    }
}
