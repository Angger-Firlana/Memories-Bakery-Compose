package com.example.kenanganbakery.domain.models.production_schedule

import com.example.kenanganbakery.domain.models.menu.Menu

data class GetProductionScheduleResponse(
    val success:Boolean,
    val message:String,
    val data:List<ProductionSchedule>
)

data class ProductionSchedule(
    val id:Int,
    val branch_id:Int,
    val schedule_date:String,
    val status:String,
    val production_details: List<ProductionScheduleDetail>,

)

data class ProductionScheduleDetail(
    val id:Int,
    val production_schedule_id:Int,
    val menu_id:Int,
    val quantity:Int,
    val menu: Menu
)

data class PatchStatusProductionScheduleRequest(
    val status:String
)

data class HitProductionScheduleResponse(
    val status:Boolean,
    val message: String
)