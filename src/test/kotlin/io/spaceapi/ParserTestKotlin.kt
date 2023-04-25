package io.spaceapi

import io.spaceapi.types.Contact
import io.spaceapi.types.SpaceFed
import io.spaceapi.types.State
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.junit.Assert
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTestKotlin {
    @Test
    fun testParseV13FromString() {
        val parsed = parseString("""
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

        assertEquals(false, parsed.state!!.open)
        assertEquals(null, parsed.state!!.lastchange)
        assertEquals("Open Mondays from 20:00", parsed.state!!.message)
        assertEquals("Open Mondays from 20:00", parsed.state!!.message)

        assertEquals("vorstand@lists.coredump.ch", parsed.contact.email)
        assertEquals("irc://freenode.net/#coredump", parsed.contact.irc)
        assertEquals("@coredump_ch", parsed.contact.twitter)
        assertEquals(null, parsed.contact.facebook)

        @Suppress("DEPRECATION")
        assertEquals(setOf("email", "twitter"), parsed.issue_report_channels)
    }

    @Test
    fun testParseV14FromString() {
        val parsed = parseString("""
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

        assertEquals(false, parsed.state!!.open)
        assertEquals(null, parsed.state!!.lastchange)
        assertEquals("Open Mondays from 20:00", parsed.state!!.message)
        assertEquals("Open Mondays from 20:00", parsed.state!!.message)

        assertEquals("vorstand@lists.coredump.ch", parsed.contact.email)
        assertEquals("irc://freenode.net/#coredump", parsed.contact.irc)
        assertEquals("@coredump_ch", parsed.contact.twitter)
        assertEquals(null, parsed.contact.facebook)

        @Suppress("DEPRECATION")
        assertEquals(setOf("email", "twitter"), parsed.issue_report_channels)
    }

    private val minimalV14Data = """{
        "api_compatibility": ["14"],
        "space": "Coredump",
        "logo": "https://www.coredump.ch/wp-content/uploads/2016/11/logo.png",
        "url": "https://www.coredump.ch/",
        "location": {"lon": 47.2251, "lat": 8.8339},
        "contact": {}
    }"""

    @Test
    fun testParseV14Minimal() {
        val parsed = parseString(minimalV14Data)

        assertEquals(setOf("14"), parsed.api_compatibility)
        assertEquals("Coredump", parsed.space)
        assertEquals("https://www.coredump.ch/wp-content/uploads/2016/11/logo.png", parsed.logo)
        assertEquals(URL("https://www.coredump.ch/"), parsed.url)
        assertEquals(null, parsed.location.address)
        assertEquals(47.2251f, parsed.location.lon)
        assertEquals(8.8339f, parsed.location.lat)

        @Suppress("DEPRECATION")
        assertEquals(emptySet(), parsed.issue_report_channels)
        assertEquals(null, parsed.state)
    }

    /**
     * Test decoding from a JsonElement.
     */
    @Test
    fun testParseV14FromJsonElement() {
        val map: HashMap<String, JsonElement> = Json.decodeFromString(minimalV14Data)
        val parsed = fromJsonElement(JsonObject(map))
        assertEquals("Coredump", parsed.space)
        assertEquals(setOf("14"), parsed.api_compatibility)
    }

    /**
     * Regression test for #2.
     */
    @Test
    fun parseFloatAsInteger() {
        val parsed: State = Json.decodeFromString("""{
            "open": false,
            "message": "Open Mondays from 20:00",
            "lastchange": 1605400210.0
        }""")
        assertEquals(false, parsed.open)
        assertEquals("Open Mondays from 20:00", parsed.message)
        assertEquals(1605400210L, parsed.lastchange)
    }

    /**
     * Regression test for #5.
     */
    @Test
    fun parseFacebookNonUrl() {
        val parsed: Contact = Json.decodeFromString("""{"facebook": "SomeGroupName"}""")
        assertEquals("SomeGroupName", parsed.facebook)
    }

    /**
     * Helper function.
     */
    private fun assertThrowsParseError(input: String, expectedMessage: String) {
        try {
            parseString(input)
            Assert.fail("Did not throw ParseError")
        } catch (e: ParseError) {
            assertEquals(expectedMessage, e.message)
        }
    }

    @Test
    fun parseInvalidBlankOrEmpty() {
        assertThrowsParseError("", "Input JSON is blank or empty")
        assertThrowsParseError("    ", "Input JSON is blank or empty")
    }

    @Test
    fun parseInvalidNotJson() {
        assertThrowsParseError("/*{not json}*/", "Input JSON does not start with '{'. Is it really JSON?")
    }

    @Test
    fun parseInvalidApiVersion() {
        assertThrowsParseError("""{"api": "0.12", "space": "Foobar"}""", "Unsupported API version: 0.12")
        assertThrowsParseError("""{"api": "0.11", "space": "Foobar"}""", "Unsupported API version: 0.11")
    }

    /**
     * The `jabber` field was renamed to `xmpp` in v14. Ensure that it's parsed and remapped correctly.
     */
    @Test
    fun parseJabberXmppField() {
        val jsonBase = """{
            "api": "0.13",
            "api_compatibility": ["14"],
            "space": "Coredump",
            "logo": "https://www.coredump.ch/wp-content/uploads/2016/11/logo.png",
            "url": "https://www.coredump.ch/",
            "location": {"lon": 47.2251, "lat": 8.8339},
            "contact":
        """

        // Only jabber specified
        val parsed1 = parseString("$jsonBase{\"jabber\": \"foo@bar.com\"}}")
        assertEquals("foo@bar.com", parsed1.contact.xmpp)

        // Only xmpp specified
        val parsed2 = parseString("$jsonBase{\"xmpp\": \"foo@bar.com\"}}")
        assertEquals("foo@bar.com", parsed2.contact.xmpp)

        // Both jabber and xmpp specified, xmpp wins
        val parsed3 = parseString("$jsonBase{\"jabber\": \"old@bar.com\", \"xmpp\": \"foo@bar.com\"}}")
        assertEquals("foo@bar.com", parsed3.contact.xmpp)
    }

    /**
     * Parse errors should be wrapped in the `ParseError` class.
     */
    @Test
    fun parseErrorWrapping() {
        try {
            parseString("""{"api": "0.13"}""")
        } catch (e: ParseError) {
            /* Success! */
            Assert.assertTrue(e.cause != null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail("Unwrapped exception thrown!")
        }
    }

    @Test
    fun parseSpaceFedWithPhone() {
        val parsed: SpaceFed = Json.decodeFromString("""{
            "spacesaml": true,
            "spacenet": false,
            "spacephone": true
        }""")
        assertEquals(true, parsed.spacesaml)
        assertEquals(false, parsed.spacenet)
        assertEquals(true, parsed.spacephone)
    }

    /**
     * Regression test for #34.
     */
    @Test
    fun parseSpaceFedWithoutPhone() {
        val parsed: SpaceFed = Json.decodeFromString("""{
            "spacesaml":false,
            "spacenet":true
        }""")
        assertEquals(false, parsed.spacesaml)
        assertEquals(true, parsed.spacenet)
        assertEquals(false, parsed.spacephone)
    }
}
