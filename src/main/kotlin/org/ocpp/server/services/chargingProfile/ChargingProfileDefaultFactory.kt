package org.ocpp.server.services.chargingProfile

import eu.chargetime.ocpp.model.core.*
import org.isc.utils.services.dateTime.DateTimeUtil
import org.isc.utils.utils.Ids
import org.ocpp.server.services.chargingProfile.`interface`.IChargingProfileDefaultFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ChargingProfileDefaultFactory : IChargingProfileDefaultFactory {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun build(): ChargingProfile {
        logger.info("Building default charging profile")
        val profile = buildDefaultProfile()
        profile.transactionId = Ids.getRandomIdentifier()
        profile.recurrencyKind = RecurrencyKindType.Daily
        profile.validFrom = DateTimeUtil.dateNow()
        profile.validTo = ZonedDateTime.now().plusWeeks(1)
        return profile
    }

    private fun buildSchedule(): ChargingSchedule {
        val startPeriod = 0
        val limit = 1000.0
        val period = ChargingSchedulePeriod(startPeriod, limit)
        return ChargingSchedule(ChargingRateUnitType.W, arrayOf(period))
    }

    private fun buildDefaultProfile(): ChargingProfile {
        val chargingProfileId = Ids.getRandomIdentifier()
        val stackLevel = 0
        return ChargingProfile(
            chargingProfileId,
            stackLevel,
            ChargingProfilePurposeType.TxProfile,
            ChargingProfileKindType.Relative,
            buildSchedule()
        )
    }
}
