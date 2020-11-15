@file:UseSerializers(URLSerializer::class, RoundingLongSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.RoundingLongSerializer
import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URL

@Serializable
data class State(
        val open: Boolean?,
        val lastchange: Long? = null, // TODO: Make this a date?
        val trigger_person: String? = null,
        val message: String? = null,
        val icon: Icon? = null,
)

@Serializable
data class Icon(
        val open: URL,
        val closed: URL,
)