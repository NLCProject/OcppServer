package org.ocpp.server.services.authentication.interfaces

import org.isc.utils.genericCrudl.interfaces.IControllerPermissionService

/**
 * Services authorizes all incoming REST requests.
 */
interface IAuthenticationService : IControllerPermissionService
