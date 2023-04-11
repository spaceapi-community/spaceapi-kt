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
@file:UseSerializers(URISerializer::class, URLSerializer::class)

package io.spaceapi.types

import io.spaceapi.types.serializers.URISerializer
import io.spaceapi.types.serializers.URLSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.net.URI

@Serializable
data class Contact(
    @JvmField
    var phone: String? = null,
    @JvmField
    var sip: URI? = null,
    @JvmField
    var keymasters: Array<Keymaster> = emptyArray(),
    @JvmField
    var irc: String? = null,
    @JvmField
    var twitter: String? = null,
    @JvmField
    var mastodon: String? = null,
    @JvmField
    var facebook: String? = null,
    // Note: google plus not supported, it's dead anyway
    @JvmField
    val identica: String? = null,
    @JvmField
    var foursquare: String? = null,
    @JvmField
    var email: String? = null,
    @JvmField
    var ml: String? = null,
    @Deprecated("The 'jabber' field was renamed to 'xmpp' in API v14")
    internal var jabber: String? = null,
    @JvmField
    var xmpp: String? = null,
    @JvmField
    var issue_mail: String? = null,
    @JvmField
    var gopher: String? = null,
    @JvmField
    var matrix: String? = null,
    @JvmField
    var mumble: String? = null,
)

@Serializable
data class Keymaster(
    @JvmField
    var name: String? = null,
    @JvmField
    var irc_nick: String? = null,
    @JvmField
    var phone: String? = null,
    @JvmField
    var email: String? = null,
    @JvmField
    var twitter: String? = null,
    @JvmField
    var xmpp: String? = null,
    @JvmField
    var mastodon: String? = null,
)
