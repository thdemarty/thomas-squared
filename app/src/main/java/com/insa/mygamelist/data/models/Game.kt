package com.insa.mygamelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A video game
 */
@Serializable
data class Game(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("cover") val cover: Cover,
    @SerialName("first_release_date") val firstReleaseDate: Int? = null,
    @SerialName("genres") val genres: List<Genre> = emptyList(),
    @SerialName("platforms") val platforms: List<Platform> = emptyList(),
    @SerialName("summary") val summary: String? = "",
    @SerialName("total_rating") val totalRating: Double? = null
)
