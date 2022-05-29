package org.ocpp.server.entities.meterValue.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.meterValue.MeterValueEntity
import java.util.*

/**
 *
 */
interface IMeterValueRepository : ICrudlRepository<MeterValueEntity>