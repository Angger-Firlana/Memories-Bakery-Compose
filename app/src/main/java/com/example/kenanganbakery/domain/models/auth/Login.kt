package com.example.kenanganbakery.domain.models.auth

import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.user.User

data class LoginRequest(
    val login:String,
    val password:String
)

data class LoginResponse(
    val success:Boolean,
    val message:String,
    val user:User,
    val branch: Branch?=null,
    val token:String
)