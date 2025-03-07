package com.insa.mygamelist.data.apis

import com.insa.mygamelist.data.models.Game
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IgdbApi {
    @POST("games")
    @Headers("Content-Type: text/plain")
    suspend fun getGames(@Body query: RequestBody): Response<List<Game>>

    @POST("games")
    @Headers("Content-Type: text/plain")
    suspend fun searchGame(@Body query: RequestBody): Response<List<Game>>
}