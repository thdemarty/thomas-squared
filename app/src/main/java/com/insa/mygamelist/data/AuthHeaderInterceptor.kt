package com.insa.mygamelist.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor(private val clientId: String, private var accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("HeaderInterceptor", "Intercept with accessToken: $accessToken")

        val request = chain.request().newBuilder()
            .addHeader("Client-ID", clientId)
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(request)
    }
    fun updateToken(newToken: String) {
        Log.d("HeaderInterceptor", "updateToken: $newToken")
        accessToken = newToken
    }
}