package com.example.kenanganbakery.data.remote

import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse
import com.example.kenanganbakery.domain.models.auth.RegisterRequest
import com.example.kenanganbakery.domain.models.auth.RegisterResponse
import com.example.kenanganbakery.domain.models.branch.GetBranchResponse
import com.example.kenanganbakery.domain.models.menu.GetMenuResponse
import com.example.kenanganbakery.domain.models.order.GetOrderResponse
import com.example.kenanganbakery.domain.models.order.HitOrderResponse
import com.example.kenanganbakery.domain.models.order.PostOrderRequest
import com.example.kenanganbakery.domain.models.order.UpdateOrderRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @POST("auth/login")
    suspend fun login(
        @Body
        request:LoginRequest
    ):Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(
        @Body
        request:RegisterRequest
    ):Response<RegisterResponse>

    @GET("menus")
    suspend fun indexMenus(
        @Query("category") category: String? = null,
        @Query("search") search: String? = null
    ): Response<GetMenuResponse>


    @GET("branchs")
    suspend fun indexBranch(
        @Query("search") search: String? = null
    ): Response<GetBranchResponse>

    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /////////////////////////////ORDERRRRRR?///////////////////////////////////////
    @GET("orders")
    suspend fun indexOrder():Response<GetOrderResponse>

    @POST("orders")
    suspend fun postOrder(@Body request:PostOrderRequest):Response<HitOrderResponse>

    @PUT("orders/{id}")
    suspend fun putOrder(@Body request:UpdateOrderRequest):Response<HitOrderResponse>
}