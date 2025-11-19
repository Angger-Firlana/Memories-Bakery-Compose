package com.example.kenanganbakery.domain.models.auth

data class LoginRequest(
    val login:String,
    val password:String
)

data class LoginResponse(
    val success:Boolean,
    val token:String
)