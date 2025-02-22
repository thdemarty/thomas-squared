package com.insa.mygamelist.data.apis

import com.insa.mygamelist.data.models.TokenResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthApi {
    /**
     * Get an bearer access token for the IGDB api.
     * [See documentation](https://api-docs.igdb.com/#authentication)
     */
    @POST("token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String = "client_credentials"
    ): Response<TokenResponse>
}