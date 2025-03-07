package com.insa.mygamelist.data

import com.insa.mygamelist.data.apis.AuthApi
import com.insa.mygamelist.data.apis.IgdbApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object IGDB {
    private const val BASE_URL_IGDB: String = "https://api.igdb.com/v4/"
    private const val BASE_URL_AUTH: String = "https://id.twitch.tv/oauth2/"
    const val CLIENT_ID: String = "ufaqx982uctnp7m3l8mlj8adu3e9py"
    const val CLIENT_SECRET: String = "rwbl2b9n4bc19f17fxivuj4vakzdrn"
    private var accessToken: String = ""

    private val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val authClient = OkHttpClient.Builder()
        .build()

    private val igdbClient = OkHttpClient.Builder()
        .addInterceptor(AuthHeaderInterceptor(CLIENT_ID, accessToken))
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .client(authClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AuthApi::class.java)
    }

    val igdbApi: IgdbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_IGDB)
            .client(igdbClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(IgdbApi::class.java)
    }

    fun setAccessToken(token: String) {
        accessToken = token
        igdbClient.interceptors.filterIsInstance<AuthHeaderInterceptor>().forEach {
            it.updateToken(accessToken)
        }
    }
}