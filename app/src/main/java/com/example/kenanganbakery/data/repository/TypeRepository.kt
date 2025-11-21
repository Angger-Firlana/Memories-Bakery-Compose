package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.order.GetOrderResponse
import com.example.kenanganbakery.domain.models.type.GetTypeResponse

class TypeRepository(context: Context) {

    private val api = APIClient.getClient(context)

    suspend fun getAllType():Result<GetTypeResponse>{
        return try{
            val response = api.indexType()

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