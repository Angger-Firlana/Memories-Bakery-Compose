package com.example.kenanganbakery.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.repository.ProductionScheduleRepository
import com.example.kenanganbakery.domain.models.production_schedule.PatchStatusProductionScheduleRequest
import com.example.kenanganbakery.domain.models.production_schedule.ProductionSchedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductionScheduleViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ProductionScheduleRepository(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _schedules = MutableStateFlow<List<ProductionSchedule>>(emptyList())
    val schedules = _schedules.asStateFlow()

    fun getAllSchedule(
        search:String?=null,
        date:String?=null
    ){
        viewModelScope.launch {
            val result = repository.getAllProductionSchedule(search = search, date = date)

            result.fold(
                onSuccess = { body->
                    _schedules.value = body.data
                },
                onFailure = {

                }
            )
        }
    }

    fun updateStatus(
        status:String
    ){
        val request = PatchStatusProductionScheduleRequest(status = status)
        viewModelScope.launch {
            val result = repository.updateStatus(request)

            result.fold(
                onSuccess = { body->
                    _state.value = true
                },
                onFailure = {
                    _state.value = false
                }
            )
        }
    }

    fun clearState(){
        _state.value = null
    }
}