package org.ocpp.server.entities.sampledValue.interfaces

import org.isc.utils.genericCrudl.interfaces.ICrudlRepository
import org.ocpp.server.entities.connectors.ConnectorEntity
import org.ocpp.server.entities.meterValue.MeterValueEntity
import org.ocpp.server.entities.sampledValue.SampledValueEntity
import java.util.*

/**
 *
 */
interface ISampledValueRepository : ICrudlRepository<SampledValueEntity>