package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.order.GetOrderResponse
import com.example.kenanganbakery.domain.models.order.HitOrderResponse
import com.example.kenanganbakery.domain.models.order.PostOrderRequest
import com.example.kenanganbakery.domain.models.order.UpdateOrderRequest

class OrderRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun getAllOrders():Result<GetOrderResponse>{
        return try{
            val response = api.indexOrder()

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

    suspend fun postOrder(request: PostOrderRequest):Result<HitOrderResponse>{
        return try{
            val response = api.postOrder(request)

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

    suspend fun putOrder(request: UpdateOrderRequest):Result<HitOrderResponse>{
        return try{
            val response = api.putOrder(request)

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