@file:UseSerializers(URLSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

val format = Json { ignoreUnknownKeys = true }

/**
 * Main entry point into the library.
 *
 * Parse a JSON string, return a `Status` instance.
 */
fun decodeFromString(json: String): Status {
    return format.decodeFromString(json)
}

@Serializable
data class Status(
        val space: String,
        val api: String? = null,
        val api_compatibility: Set<String> = emptySet(),
        val logo: String,
        val url: URL,
        val location: Location,
        val state: State,
        val contact: Contact,
        val issue_report_channels: List<String>,
)