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
@file:UseSerializers(URLSerializer::class, RoundingLongSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.RoundingLongSerializer
import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URL

@Serializable
data class State(
    var open: Boolean?,
    var lastchange: Long? = null, // TODO: Make this a date?
    var trigger_person: String? = null,
    var message: String? = null,
    var icon: Icon? = null,
)

@Serializable
data class Icon(
    var open: URL,
    var closed: URL,
)
