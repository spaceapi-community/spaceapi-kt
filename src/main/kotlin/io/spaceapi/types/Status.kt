@file:UseSerializers(URLSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URL

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