package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse
import com.example.kenanganbakery.domain.models.menu.GetMenuResponse

class MenuRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun indexMenu(category:String?=null, search:String?=null):Result<GetMenuResponse>{
        return try{
            val response = api.indexMenus(category = category, search = search)

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