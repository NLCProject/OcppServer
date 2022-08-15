package org.ocpp.server.services.migration.data

object SmartHomes {

    val data = listOf(
        SmartHome(name = "Smart Home #1", id = "83ab1a38-ea46-40b2-8a63-ee61ed57355a"),
        SmartHome(name = "Smart Home #2", id = "ebe3c27b-364b-45d0-af41-46f411251f99")
    )

    class SmartHome(val name: String, val id: String)
}
