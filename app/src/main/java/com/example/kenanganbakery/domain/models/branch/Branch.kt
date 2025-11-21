package com.example.kenanganbakery.domain.models.branch

data class GetBranchResponse(
    val success:Boolean,
    val message:String,
    val data: List<Branch>
)
data class Branch(
    val id:Int,
    val name:String,
    val address:String,
    val city:String,
    val province:String,
    val open:Int,
    val close:Int,
    val phone_number:String,
    val email:String,
)
