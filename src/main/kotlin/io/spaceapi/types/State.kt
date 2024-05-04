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
@file:UseSerializers(TimestampSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.TimestampSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Instant

@Serializable
data class State(
    @JvmField
    var open: Boolean? = null,
    @JvmField
    var lastchange: Instant? = null,
    @JvmField
    var trigger_person: String? = null,
    @JvmField
    var message: String? = null,
    @JvmField
    var icon: Icon? = null,
)

@Serializable
data class Icon(
    @JvmField
    var open: String,
    @JvmField
    var closed: String,
)
