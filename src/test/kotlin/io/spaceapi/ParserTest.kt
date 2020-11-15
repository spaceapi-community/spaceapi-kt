package io.spaceapi

import io.spaceapi.types.Status
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
    @Test fun testParseStatus() {
        val exampleV13 = """
            {
              "api": "0.13",
              "space": "Coredump",
              "logo": "https://www.coredump.ch/wp-content/uploads/2016/11/logo.png",
              "url": "https://www.coredump.ch/",
              "location": {
                "address": "Neue Jonastrasse 107, 8640 Rapperswil, Switzerland",
                "lon": 47.2251,
                "lat": 8.8339
              },
              "contact": {
                "email": "vorstand@lists.coredump.ch",
                "irc": "irc://freenode.net/#coredump",
                "twitter": "@coredump_ch"
              },
              "issue_report_channels": ["email", "twitter"],
              "state": {
                "open": false,
                "message": "Open Mondays from 20:00"
              }
            }
        """.trimIndent()
        val parsed: Status = fromJson(exampleV13)
        assertEquals("Coredump", parsed.space)
        assertEquals("0.13", parsed.api)
    }
}
