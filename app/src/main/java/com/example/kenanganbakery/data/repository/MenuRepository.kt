package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse
import com.example.kenanganbakery.domain.models.menu.GetMenuResponse

class MenuRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun indexMenu(
        category: String? = null,
        search: String? = null
    ): Result<GetMenuResponse> {
        return try {
            val response = api.indexMenus(category = category, search = search)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                // Ambil error body string agar lebih jelas
                val errorString = response.errorBody()?.string() ?: "Unknown error body"

                Result.failure(
                    Exception(
                        "HTTP ${response.code()} - ${response.message()}\nServer response: $errorString"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(
                Exception("Network/Unexpected error: ${e.localizedMessage ?: e.toString()}")
            )
        }
    }

}