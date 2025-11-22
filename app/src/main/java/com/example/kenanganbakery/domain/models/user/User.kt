package com.example.kenanganbakery.domain.models.user

data class Role(
    val id:Int,
    val role_name:String,
    val created_at:String
)

data class User(
    val id:Int,
    val username:String,
    val email:String,
    val role_id:Int,
    val created_at:String?=null,
    val role:Role?=null
)
