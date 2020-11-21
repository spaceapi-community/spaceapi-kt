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
    var phone: String? = null,
    var sip: URI? = null,
    var keymasters: Array<Keymaster> = emptyArray(),
    var irc: String? = null,
    var twitter: String? = null,
    var mastodon: String? = null,
    var facebook: String? = null,
    // Note: google plus not supported, it's dead anyways
    val identica: String? = null,
    var foursquare: String? = null,
    var email: String? = null,
    var ml: String? = null,
    @Deprecated("The 'jabber' field was renamed to 'xmpp' in API v14")
    internal var jabber: String? = null,
    var xmpp: String? = null,
    var issue_mail: String? = null,
    var gopher: String? = null,
    var matrix: String? = null,
    var mumble: String? = null,
)

@Serializable
data class Keymaster(
    var name: String? = null,
    var irc_nick: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var twitter: String? = null,
    var xmpp: String? = null,
    var mastodon: String? = null,
)
