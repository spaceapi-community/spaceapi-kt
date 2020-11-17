/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020 Danilo Bargen
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

// This is the main entry point into the library.

val format = Json { ignoreUnknownKeys = true }

/**
 * Parse a JSON string, return a `Status` instance.
 */
fun parseString(json: String): Status {
    return format.decodeFromString(json)
}

/**
 * Parse a `JsonElement`, return a `Status` instance.
 */
fun fromJsonElement(json: JsonElement): Status {
    return format.decodeFromJsonElement(json)
}
