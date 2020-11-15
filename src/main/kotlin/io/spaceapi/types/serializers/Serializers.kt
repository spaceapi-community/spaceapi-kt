package io.spaceapi.types.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URI
import java.net.URL
import kotlin.math.round

@ExperimentalSerializationApi
@Serializer(forClass = URL::class)
object URLSerializer : KSerializer<URL> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("URL", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: URL) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): URL = URL(decoder.decodeString())
}

@ExperimentalSerializationApi
@Serializer(forClass = URI::class)
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
@Serializer(forClass = Long::class)
object RoundingLongSerializer : KSerializer<Long> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RoundingLong", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Long) = encoder.encodeLong(value)
    override fun deserialize(decoder: Decoder): Long = round(decoder.decodeDouble()).toLong()
}