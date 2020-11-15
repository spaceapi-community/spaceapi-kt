package io.spaceapi

import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
    @Test
    fun testParseV13() {
        val parsed = fromJson("""
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
        """)

        assertEquals("Coredump", parsed.space)
        assertEquals("0.13", parsed.api)
        assertEquals(emptySet(), parsed.api_compatibility)
        assertEquals("https://www.coredump.ch/wp-content/uploads/2016/11/logo.png", parsed.logo)
        assertEquals("https://www.coredump.ch/", parsed.url.toString())

        assertEquals("Neue Jonastrasse 107, 8640 Rapperswil, Switzerland", parsed.location.address)
        assertEquals(47.2251f, parsed.location.lon)
        assertEquals(8.8339f, parsed.location.lat)

        assertEquals(false, parsed.state.open)
        assertEquals(null, parsed.state.lastchange)
        assertEquals("Open Mondays from 20:00", parsed.state.message)
        assertEquals("Open Mondays from 20:00", parsed.state.message)

        assertEquals("vorstand@lists.coredump.ch", parsed.contact.email)
        assertEquals("irc://freenode.net/#coredump", parsed.contact.irc)
        assertEquals("@coredump_ch", parsed.contact.twitter)
        assertEquals(null, parsed.contact.facebook)

        assertEquals(listOf("email", "twitter"), parsed.issue_report_channels)
    }

    @Test
    fun testParseV14() {
        val parsed = fromJson("""
            {
              "api": "0.13",
              "api_compatibility": ["14"],
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
        """)

        assertEquals("Coredump", parsed.space)
        assertEquals("0.13", parsed.api)
        assertEquals(setOf("14"), parsed.api_compatibility)
        assertEquals("https://www.coredump.ch/wp-content/uploads/2016/11/logo.png", parsed.logo)
        assertEquals("https://www.coredump.ch/", parsed.url.toString())

        assertEquals("Neue Jonastrasse 107, 8640 Rapperswil, Switzerland", parsed.location.address)
        assertEquals(47.2251f, parsed.location.lon)
        assertEquals(8.8339f, parsed.location.lat)

        assertEquals(false, parsed.state.open)
        assertEquals(null, parsed.state.lastchange)
        assertEquals("Open Mondays from 20:00", parsed.state.message)
        assertEquals("Open Mondays from 20:00", parsed.state.message)

        assertEquals("vorstand@lists.coredump.ch", parsed.contact.email)
        assertEquals("irc://freenode.net/#coredump", parsed.contact.irc)
        assertEquals("@coredump_ch", parsed.contact.twitter)
        assertEquals(null, parsed.contact.facebook)

        assertEquals(listOf("email", "twitter"), parsed.issue_report_channels)
    }
}
