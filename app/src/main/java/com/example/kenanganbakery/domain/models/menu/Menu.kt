package com.example.kenanganbakery.domain.models.menu

import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.Branch

data class GetMenuResponse(
    val success:Boolean,
    val message:String,
    val current_page:Int,
    val per_page:Int,
    val total:Int,
    val last_page:Int,
    val data: List<Menu>
)

data class Menu(
    val id:Int,
    val type_id:Int,
    val branch_id:Int,
    val name:String,
    val price:Int,
    val description:String,
    val validDuration:Int,
    val branch:Branch?=null
)
