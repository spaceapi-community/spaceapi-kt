/**
 * spaceapi-kt
 * https://github.com/spaceapi-community/spaceapi-kt/
 * Copyright (C) 2020-2024 Danilo Bargen
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
package io.spaceapi.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URL

// Note: In general, when dealing with optional collections, those use an empty collection
// as the default value instead of null.

@Serializable
data class Status(
    @JvmField
    var api: String? = null,
    @JvmField
    var api_compatibility: Set<String> = emptySet(),
    @JvmField
    var space: String,
    @JvmField
    var logo: String,
    @JvmField
    var url: String,
    @JvmField
    var location: Location,
    @JvmField
    var spacefed: SpaceFed? = null,
    @JvmField
    var cam: Array<String> = emptyArray(),
    @Deprecated("The 'stream' field was removed in API v14")
    @JvmField
    var stream: Stream? = null,
    @JvmField
    var state: State? = null,
    @JvmField
    var events: Array<Event> = emptyArray(),
    @JvmField
    var contact: Contact,
    @Deprecated("The 'issue_report_channels' field was removed in API v14")
    @JvmField
    var issue_report_channels: Set<String> = emptySet(),
    @JvmField
    var sensors: Sensors? = null,
    @JvmField
    var feeds: Feeds? = null,
    @Deprecated("The 'cache' field was removed in API v14")
    @JvmField
    var cache: Cache? = null,
    @JvmField
    var projects: Array<String> = emptyArray(),
    @JvmField
    var links: Array<Link> = emptyArray(),
    @JvmField
    var membership_plans: Array<MembershipPlan> = emptyArray(),
    @Deprecated("The 'radio_show' field was removed in API v14")
    @JvmField
    var radio_show: Array<RadioShow> = emptyArray(),
)
