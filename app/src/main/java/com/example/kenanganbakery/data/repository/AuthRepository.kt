package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse

class AuthRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun login(request:LoginRequest):Result<LoginResponse>{
        return try{
            val response = api.login(request)

            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    Result.success(body)
                }else{
                    Result.failure(Exception("null body"))
                }
            }else{
                Result.failure(Exception("Error : ${response.errorBody()}"))
            }
        }catch(e:Exception){
            Result.failure(e)
        }
    }
}