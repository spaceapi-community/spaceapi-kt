package io.spaceapi

import kotlinx.serialization.*
import kotlinx.serialization.json.*

import io.spaceapi.types.Status

val format = Json { ignoreUnknownKeys = true }

/**
 * Parse a JSON string, return a `Status` instance.
 */
fun fromJson(json: String): Status {
    return format.decodeFromString(json)
}