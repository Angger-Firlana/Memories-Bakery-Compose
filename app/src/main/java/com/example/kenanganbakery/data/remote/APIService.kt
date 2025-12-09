package com.example.kenanganbakery.data.remote

import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.domain.models.auth.LoginResponse
import com.example.kenanganbakery.domain.models.auth.RegisterRequest
import com.example.kenanganbakery.domain.models.auth.RegisterResponse
import com.example.kenanganbakery.domain.models.branch.GetBranchResponse
import com.example.kenanganbakery.domain.models.delivery.Delivery
import com.example.kenanganbakery.domain.models.delivery.GetDeliveryResponse
import com.example.kenanganbakery.domain.models.delivery.HitDeliveryResponse
import com.example.kenanganbakery.domain.models.menu.GetMenuDetailResponse
import com.example.kenanganbakery.domain.models.menu.GetMenuResponse
import com.example.kenanganbakery.domain.models.order.GetDetailOrderResponse
import com.example.kenanganbakery.domain.models.order.GetOrderResponse
import com.example.kenanganbakery.domain.models.order.HitOrderResponse
import com.example.kenanganbakery.domain.models.order.PostOrderRequest
import com.example.kenanganbakery.domain.models.order.UpdateOrderRequest
import com.example.kenanganbakery.domain.models.production_schedule.GetProductionScheduleResponse
import com.example.kenanganbakery.domain.models.production_schedule.HitProductionScheduleResponse
import com.example.kenanganbakery.domain.models.production_schedule.PatchStatusProductionScheduleRequest
import com.example.kenanganbakery.domain.models.type.GetTypeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

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

    @GET("types")
    suspend fun indexType():Response<GetTypeResponse>

    @GET("menus")
    suspend fun indexMenus(
        @Query("category") category: String? = null,
        @Query("search") search: String? = null
    ): Response<GetMenuResponse>

    @GET("menus/{id}")
    suspend fun getMenu(@Path("id") id:Int):Response<GetMenuDetailResponse>

    @GET
    suspend fun getImage(@Url url:String):Response<ResponseBody>


    @GET("branchs")
    suspend fun indexBranch(
        @Query("search") search: String? = null
    ): Response<GetBranchResponse>

    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /////////////////////////////ORDERRRRRR?///////////////////////////////////////
    @GET("orders")
    suspend fun indexOrder():Response<GetOrderResponse>

    @GET("orders/{id}")
    suspend fun detailOrder(@Path("id") id:Int):Response<GetDetailOrderResponse>

    @POST("orders")
    suspend fun postOrder(@Body request:PostOrderRequest):Response<HitOrderResponse>

    @PUT("orders/{id}")
    suspend fun putOrder(@Body request:UpdateOrderRequest):Response<HitOrderResponse>

    //
    @GET("production-schedules")
    suspend fun indexProductionSchedule(
        @Query("search") search: String? = null,
        @Query("date") date: String? = null
    ): Response<GetProductionScheduleResponse>

    @PATCH("production-schedules/details/{id}/status")
    suspend fun patchStatusProductionScheduleDetail(@Body request:PatchStatusProductionScheduleRequest, @Path("id") id:Int):Response<HitProductionScheduleResponse>


    @PATCH("production-schedules/{id}")
    suspend fun patchStatusProductionSchedule(@Body request:PatchStatusProductionScheduleRequest, @Path("id") id:Int):Response<HitProductionScheduleResponse>

    //Delivery
    @GET("deliveries/users/{id}")
    suspend fun indexDeliveryByUser(@Path("id") id:Int):Response<GetDeliveryResponse>

    @POST("deliveries")
    suspend fun postDelivery(@Body request:Delivery):Response<HitDeliveryResponse>

    @PATCH("deliveries/{id}")
    suspend fun patchDelivery(@Path("id") id:Int,@Body request:Delivery):Response<HitDeliveryResponse>

}