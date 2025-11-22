package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.production_schedule.GetProductionScheduleResponse
import com.example.kenanganbakery.domain.models.production_schedule.HitProductionScheduleResponse
import com.example.kenanganbakery.domain.models.production_schedule.PatchStatusProductionScheduleRequest

class ProductionScheduleRepository(context:Context) {
    private val api = APIClient.getClient(context)

    suspend fun getAllProductionSchedule(
        search:String?,
        date:String?
    ):Result<GetProductionScheduleResponse>{
        return try{
            val response = api.indexProductionSchedule(search = search, date = date)

            if (response.isSuccessful){
                val body = response.body()

                if(body != null){
                    Result.success(body)
                }else{
                    Result.failure(Exception("Response body is null"))
                }
            }else{
                Result.failure(
                    Exception("Request failed with code ${response.code()} - ${response.message()}\nServer Response: ${response.errorBody()?.string()}")
                )
            }
        }catch (e:Exception){
            Result.failure(e)
        }

    }

    suspend fun updateStatus(
        request:PatchStatusProductionScheduleRequest
    ):Result<HitProductionScheduleResponse>{
        return try{
            val response = api.patchStatusProductionSchedule(request)

            if (response.isSuccessful){
                val body = response.body()

                if(body != null){
                    Result.success(body)
                }else{
                    Result.failure(Exception("Response body is null"))
                }
            }else{
                Result.failure(
                    Exception("Request failed with code ${response.code()} - ${response.message()}\nServer Response: ${response.errorBody()?.string()}")
                )
            }
        }catch (e:Exception){
            Result.failure(e)
        }

    }
}