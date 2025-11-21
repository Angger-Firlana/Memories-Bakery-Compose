package com.example.kenanganbakery.data.remote

import android.content.Context
import android.util.Log
import com.example.kenanganbakery.data.local.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "http://10.152.197.165:8000/api/"

    fun getClient(context: Context):APIService{
        val authInterceptor = Interceptor{ chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
            val token = TokenManager(context).getToken()
            token?.let {

                builder.addHeader("Authorization", "Bearer $it")

            }
            chain.proceed(builder.build())
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}