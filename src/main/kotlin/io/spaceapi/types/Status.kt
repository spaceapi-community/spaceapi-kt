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
@file:UseSerializers(URLSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URL

// Note: In general, when dealing with optional collections, those use an empty collection
// as the default value instead of null.

@Serializable
data class Status(
    val api: String? = null,
    val api_compatibility: Set<String> = emptySet(),
    val space: String,
    val logo: String,
    val url: URL,
    val location: Location,
    val spacefed: SpaceFed? = null,
    val cam: Array<String> = emptyArray(),
    val stream: Stream? = null,
    val state: State? = null,
    val events: Array<Event> = emptyArray(),
    val contact: Contact,
    val issue_report_channels: Set<String> = emptySet(),
    // TODO sensors
    val feeds: Feeds? = null,
    val cache: Cache? = null,
    val projects: Array<String> = emptyArray(),
    val radio_show: Array<RadioShow> = emptyArray(),
)
