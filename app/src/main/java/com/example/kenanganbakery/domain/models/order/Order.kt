package com.example.kenanganbakery.domain.models.order

// ===========================
// RESPONSE MODELS
// ===========================

data class GetOrderResponse(
    val success: Boolean?,
    val message: String?,
    val meta: Meta?,        // Jika kamu mau meta pagination
    val data: List<Order>
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
    val created_at: String?,
    val updated_at: String?
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
