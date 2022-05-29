package org.smart.home.simulator.services.exceptionLogger

import org.isc.utils.enums.SportsclubExceptionI18nKey
import org.isc.utils.genericCrudl.services.exceptionHandler.AbstractExceptionLogger
import org.springframework.stereotype.Service

@Service
class ExceptionLoggerService : AbstractExceptionLogger() {

    override fun saveErrorLog(
        userId: String,
        organisationId: String,
        organisationName: String,
        className: String,
        i18nKey: SportsclubExceptionI18nKey,
        stackTrace: String,
        message: String?
    ) { }
}
