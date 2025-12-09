package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.delivery.Delivery
import com.example.kenanganbakery.domain.models.delivery.GetDeliveryResponse
import com.example.kenanganbakery.domain.models.delivery.HitDeliveryResponse

class DeliveryRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun getDeliveries(userId:Int):Result<GetDeliveryResponse>{
        return try{
            val response = api.indexDeliveryByUser(userId)

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

    suspend fun patchDelivery(orderId:Int,request: Delivery):Result<HitDeliveryResponse>{
        return try{
            val response = api.patchDelivery(orderId,request)

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