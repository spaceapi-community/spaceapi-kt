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
package io.spaceapi.types.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URI
import java.time.Instant
import kotlin.math.round

/**
 * A serializer for URIs.
 */
@ExperimentalSerializationApi
object URISerializer : KSerializer<URI> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: URI) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): URI = URI(decoder.decodeString())
}

/**
 * A serializer that supports deserializing any JSON number (including non-integral numbers) as long
 * by rounding to the closest integer.
 */
@ExperimentalSerializationApi
object RoundingLongSerializer : KSerializer<Long> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RoundingLong", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Long) = encoder.encodeLong(value)
    override fun deserialize(decoder: Decoder): Long = round(decoder.decodeDouble()).toLong()
}

/**
 * Serialize and deserialize Unix timestamps (in seconds).
 *
 * Deserializing numbers with fractional seconds is supported,
 * but the fractional part is ignored.
 */
@ExperimentalSerializationApi
object TimestampSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Timestamp", PrimitiveKind.DOUBLE)
    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeLong(value.epochSecond)
    override fun deserialize(decoder: Decoder): Instant = Instant.ofEpochSecond(round(decoder.decodeDouble()).toLong())
}
