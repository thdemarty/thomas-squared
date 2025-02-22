package com.insa.mygamelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Logo for a platform
 */
@Serializable
data class PlatformLogo(
    @SerialName("id") val id: Long,
    @SerialName("url") val url: String
) {
    fun coverURL(size: ImageSize = ImageSize.Thumb, retina: Boolean = false): String {
        var url = this.url

        var urlSize = when (size) {
            ImageSize.Thumb -> "thumb"
            ImageSize.Micro -> "micro"
            ImageSize.CoverSmall -> "cover_small"
            ImageSize.CoverBig -> "cover_big"
            ImageSize.ScreenshotMed -> "screenshot_med"
            ImageSize.ScreenshotBig -> "screenshot_big"
            ImageSize.ScreenshotHuge -> "screenshot_huge"
            ImageSize.Res720p -> "720p"
            ImageSize.Res1080p -> "1080p"
        }

        if (retina) urlSize += "_2x"
        url = "https:" + url.replace("thumb", urlSize)
        return url
    }
}
