package io.spaceapi.types

import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val space: String,
    val api: String,
)