package com.exxuslee.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{number}")
    suspend fun getNumber(@Path("number") number: Int): Response<String>

    @GET("random/math")
    suspend fun getRandom(): Response<String>
}