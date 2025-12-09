package com.example.kenanganbakery.domain.models.delivery

data class GetDeliveryResponse(
    val success:Boolean,
    val data: List<Delivery>
)

data class Delivery(
    val id: Int?=null,
    val order_id:Int?=null,
    val courier_id:Int?=null,
    val address:String?=null,
    val fee:Int?=null,
    val delivery_date:String?=null,
    val status:String?=null,
    val created_at:String?=null
)

data class HitDeliveryResponse(
    val success: Boolean,
    val message: String,
    val data: Delivery
)
