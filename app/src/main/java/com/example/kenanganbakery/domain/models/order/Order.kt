package com.example.kenanganbakery.domain.models.order

import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.menu.Menu

// ===========================
// RESPONSE MODELS
// ===========================

data class GetOrderResponse(
    val success: Boolean?,
    val message: String?,
    val meta: Meta?,        // Jika kamu mau meta pagination
    val data: List<Order>
)

data class GetDetailOrderResponse(
    val success: Boolean?,  // Jika kamu mau meta pagination
    val data: Order
)

data class Meta(
    val current_page: Int?,
    val per_page: Int?,
    val total: Int?,
    val last_page: Int?,
    val from: Int?,
    val to: Int?
)

data class Order(
    val id: Int,
    val branch_id: Int,
    val customer_id: Int?,
    val employee_id: Int?,
    val customer_name: String?,
    val order_date: String?,
    val address: String,
    val customer_phone: String,
    val status: String,
    val total:String,
    val branch: Branch?= null,
    val order_details: List<OrderDetail>?=null,
    val created_at: String?,
    val updated_at: String?
)

data class OrderDetail(
    val id: Int,
    val order_id: Int,
    val menu_id: Int,
    val quantity: Int,
    val sub_total: Int,
    val menu: Menu
)

data class PostOrderRequest(
    val branch_id: Int,
    val customer_id: Int?,
    val employee_id: Int?,
    val customer_name: String,
    val customer_phone: String,
    val order_date: String,
    val address: String,
    val status: String,
    val details: List<OrderDetailRequest>
)

data class UpdateOrderRequest(
    val branch_id: Int,
    val customer_id: Int?,
    val employee_id: Int?,
    val customer_name: String,
    val customer_phone: String,
    val order_date: String,
    val address: String,
    val status: String,
    val details: List<OrderDetailRequest>
)

data class HitOrderResponse(
    val success: Boolean?,
    val message: String?
)

data class OrderDetailRequest(
    val menu_id: Int,
    val quantity: Int
)
