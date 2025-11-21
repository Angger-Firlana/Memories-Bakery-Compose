package com.example.kenanganbakery.data.repository

import android.content.Context
import com.example.kenanganbakery.data.remote.APIClient
import com.example.kenanganbakery.domain.models.branch.GetBranchResponse

class BranchRepository(context: Context) {
    private val api = APIClient.getClient(context)

    suspend fun indexBranch(category:String?=null, search:String?=null):Result<GetBranchResponse>{
        return try{
            val response = api.indexBranch( search = search)

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