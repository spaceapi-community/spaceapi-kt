@file:UseSerializers(URISerializer::class, URLSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.URISerializer
import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URI
import java.net.URL

@Serializable
data class Contact(
        val phone: String? = null,
        val sip: URI? = null,
        val irc: String? = null,
        val twitter: String? = null,
        val facebook: URL? = null,
        val identica: String? = null,
        val foursquare: String? = null,
        val email: String? = null,
        val ml: String? = null,
        val jabber: String? = null,
        val issue_mail: String? = null,
)