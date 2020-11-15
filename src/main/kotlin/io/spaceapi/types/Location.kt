package io.spaceapi.types

import kotlinx.serialization.Serializable

@Serializable
data class Location(
        val address: String? = null,
        val lat: Float,
        val lon: Float,
)