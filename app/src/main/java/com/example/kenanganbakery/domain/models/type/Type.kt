package com.example.kenanganbakery.domain.models.type

data class GetTypeResponse(
    val data:List<Type>
)
data class Type(
    val id:Int,
    val type_name:String
)
