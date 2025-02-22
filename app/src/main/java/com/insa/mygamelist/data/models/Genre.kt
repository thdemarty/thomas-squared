package com.insa.mygamelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Genres of video game
 */
@Serializable
data class Genre(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String
)
