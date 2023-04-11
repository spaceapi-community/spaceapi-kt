/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020-2023 Danilo Bargen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
@file:JvmName("SpaceApiParser")

package io.spaceapi

import io.spaceapi.types.Status
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

// This is the main entry point into the library.

val format = Json { ignoreUnknownKeys = true }

@Serializable
private data class StatusApi(
    val api: String? = null,
)

/**
 * An exception that is thrown if the input could not be parsed.
 */
class ParseError : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}

/**
 * Parse a JSON string, return a `Status` instance.
 */
@Throws(ParseError::class)
fun parseString(json: String): Status {
    // Sanity checks
    if (json.isBlank()) {
        throw ParseError("Input JSON is blank or empty")
    }
    if (!json.trimStart().startsWith('{')) {
        throw ParseError("Input JSON does not start with '{'. Is it really JSON?")
    }

    // Parse JSON, get "api" field (and nothing else) for API version checks
    val parsedApiField: StatusApi = format.decodeFromString(json)
    if (parsedApiField.api != null && parsedApiField.api != "0.13") {
        throw ParseError("Unsupported API version: ${parsedApiField.api}")
    }

    // Deserialize
    val decoded: Status
    try {
        decoded = format.decodeFromString(json)
    } catch (e: Exception) {
        throw ParseError("Parsing failed: ${e.message}", e)
    }

    // Map renamed fields to new name
    if (decoded.contact.jabber != null && decoded.contact.xmpp == null) {
        decoded.contact.xmpp = decoded.contact.jabber
    }

    return decoded
}

/**
 * Parse a `JsonElement`, return a `Status` instance.
 */
fun fromJsonElement(json: JsonElement): Status {
    return format.decodeFromJsonElement(json)
}
