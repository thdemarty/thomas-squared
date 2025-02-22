package com.insa.mygamelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The hardware used to run the game or game delivery network
 */
@Serializable
data class Platform(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("platform_logo") val logo: PlatformLogo? = null
)
