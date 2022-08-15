package org.ocpp.server.services.migration.data

object Connector {

    val data = listOf(
        Connector(
            name = "Solarpanel #1",
            smartHomeId = "83ab1a38-ea46-40b2-8a63-ee61ed57355a",
            externalId = ""
        ),

        Connector(
            name = "Solarpanel #2",
            smartHomeId = "83ab1a38-ea46-40b2-8a63-ee61ed57355a",
            externalId = ""
        ),

        Connector(
            name = "Power Charger #1",
            smartHomeId = "ebe3c27b-364b-45d0-af41-46f411251f99",
            externalId = ""
        ),

        Connector(
            name = "Power Charger #2",
            smartHomeId = "ebe3c27b-364b-45d0-af41-46f411251f99",
            externalId = ""
        )
    )

    class Connector(var name: String, var smartHomeId: String, var externalId: String)
}
