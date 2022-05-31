package test.client.clientView.requests

import eu.chargetime.ocpp.model.core.MeterValue
import eu.chargetime.ocpp.model.core.SampledValue
import eu.chargetime.ocpp.model.core.StartTransactionConfirmation
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.ocpp.client.application.Organisation
import org.ocpp.client.event.server.request.MeterValuesRequestEvent
import org.ocpp.client.utils.DateTimeUtil
import org.ocpp.client.utils.Ids
import org.ocpp.server.services.events.interfaces.IMeterValueHandlingService
import org.springframework.boot.test.mock.mockito.SpyBean
import test.client.clientView.requests.helper.ClientRequestTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MeterValuesRequestTest : ClientRequestTest() {

    @SpyBean
    private lateinit var meterValueHandlingService: IMeterValueHandlingService

    @Test
    fun sendRequest() {
        val sampleValueString = Ids.getRandomIdString()
        val sampledValue = SampledValue(sampleValueString)
        val meterValue = arrayOf(MeterValue(DateTimeUtil.dateNow(), arrayOf(sampledValue)))
        val connectorId = Ids.getRandomId()
        val transactionId = startTransaction().transactionId
        val confirmation = clientRequestService.meterValues(
            connectorId = connectorId,
            meterValue = meterValue,
            transactionId = transactionId
        )

        assertNotNull(confirmation)

        val argumentCaptor = argumentCaptor<MeterValuesRequestEvent>()
        verify(meterValueHandlingService, times(1)).saveMeterValue(argumentCaptor.capture())
        assertEquals(connectorId, argumentCaptor.firstValue.request.connectorId)
        assertEquals(transactionId, argumentCaptor.firstValue.request.transactionId)
        assertEquals(1, argumentCaptor.firstValue.request.meterValue.size)

        val receivedMeterValue = argumentCaptor.firstValue.request.meterValue.first()
        assertEquals(1, receivedMeterValue.sampledValue.size)
        assertNotNull(receivedMeterValue.timestamp)
        assertEquals(sampleValueString, receivedMeterValue.sampledValue.first().value)
    }

    private fun startTransaction(): StartTransactionConfirmation {
        val idTag = Organisation.validationId
        val connectorId = Ids.getRandomId()
        val meterStart = Ids.getRandomId()
        return clientRequestService.startTransaction(
            idTag = idTag,
            meterStart = meterStart,
            connectorId = connectorId
        )
    }
}
