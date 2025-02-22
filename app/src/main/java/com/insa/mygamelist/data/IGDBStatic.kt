package com.insa.mygamelist.data
/*
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.insa.mygamelist.R
import com.insa.mygamelist.data.models.Cover
import com.insa.mygamelist.data.models.Game
import com.insa.mygamelist.data.models.Genre
import com.insa.mygamelist.data.models.Platform
import com.insa.mygamelist.data.models.PlatformLogo
import kotlinx.serialization.Serializable
object IGDBStatic {

    lateinit var covers: List<Cover>
    lateinit var platformLogos: List<PlatformLogo>
    lateinit var genres: List<Genre>
    lateinit var platforms: List<Platform>
    lateinit var games: List<Game>

    fun load(context: Context) {
        // Import covers from Json
        val coversFromJson: List<Cover> = Gson().fromJson(
            context.resources.openRawResource(R.raw.covers).bufferedReader(),
            object : TypeToken<List<Cover>>() {}.type
        )

        covers = coversFromJson

        // Import Genres from Json
        val genresFromJson: List<Genre> = Gson().fromJson(
            context.resources.openRawResource(R.raw.genres).bufferedReader(),
            object : TypeToken<List<Genre>>() {}.type
        )

        genres = genresFromJson

        // Import PlatformLogos from Json
        val platformLogosFromJson: List<PlatformLogo> = Gson().fromJson(
            context.resources.openRawResource(R.raw.platform_logos).bufferedReader(),
            object : TypeToken<List<PlatformLogo>>() {}.type
        )

        platformLogos = platformLogosFromJson

        // Import Platforms from Json
        val rawPlatformsFromJson: List<RawPlatform> = Gson().fromJson(
            context.resources.openRawResource(R.raw.platforms).bufferedReader(),
            object : TypeToken<List<RawPlatform>>() {}.type
        )


        // Fix references to platform logos into platforms
        platforms = rawPlatformsFromJson.map { raw ->
            val logo = platformLogos.find { raw.logoId == it.id }
            if (logo == null) Log.e("IGDB", "No logo found for platform ${raw.name}")

            Platform(raw.id, raw.name, logo!!)
        }

        // Import Games from Json
        val gamesFromJson: List<RawGame> = Gson().fromJson(
            context.resources.openRawResource(R.raw.games).bufferedReader(),
            object : TypeToken<List<RawGame>>() {}.type
        )

        games = gamesFromJson.map { raw ->
            val cover = covers.find { it.id == raw.cover }
            val genres = genres.filter { raw.genres.contains(it.id) }
            val platforms = platforms.filter { raw.platforms.contains(it.id) }
            Game(
                raw.id,
                raw.name,
                cover!!,
                raw.firstReleaseDate,
                genres,
                platforms,
                raw.summary,
                raw.totalRating
            )
        }

        Log.d("IGDB", "Successfully loaded ${games.size} games")

    }
}


// Temporary class to import games from Json
data class RawPlatform(
    val id: Long,
    val name: String,
    @SerializedName("platform_logo") val logoId: Long
)

data class RawGame(
    val id: Long,
    val cover: Long? = null,
    val firstReleaseDate: Int,
    val genres: List<Long>,
    val name: String,
    val platforms: List<Long>,
    val summary: String,
    @SerializedName("total_rating")
    val totalRating: Double
)
*/