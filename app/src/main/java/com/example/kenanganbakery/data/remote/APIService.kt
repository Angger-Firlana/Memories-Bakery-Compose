package com.example.kenanganbakery.data.remote

import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @POST("auth/login")
    suspend fun login(
        @Body
        request:LoginRequest
    ):Response<LoginResponse>
}