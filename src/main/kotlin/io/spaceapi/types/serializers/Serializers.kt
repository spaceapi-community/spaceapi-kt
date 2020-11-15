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