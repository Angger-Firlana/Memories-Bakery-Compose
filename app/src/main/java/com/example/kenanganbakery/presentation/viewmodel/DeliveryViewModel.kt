package com.example.kenanganbakery.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.local.UserManager
import com.example.kenanganbakery.data.repository.DeliveryRepository
import com.example.kenanganbakery.domain.models.delivery.Delivery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeliveryViewModel(application: Application):AndroidViewModel(application) {
    private val repository = DeliveryRepository(application.applicationContext)
    private val userManager = UserManager(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _deliveries = MutableStateFlow<List<Delivery>>(emptyList())
    val deliveries = _deliveries.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    fun getDeliveriesByUser(){
        viewModelScope.launch {
            val userId = userManager.getUser()?.id ?: 0
            val result = repository.getDeliveries(userId)

            result.fold(
                onSuccess = { body ->
                    _deliveries.value = body.data
                },
                onFailure = {
                    _deliveries.value = emptyList()
                }
            )
        }

    }

    fun patchDelivery(request:Delivery){
        viewModelScope.launch {
            val result = repository.patchDelivery(request.order_id!!,request)
            result.fold(
                onSuccess = {
                    _state.value = true
                    _message.value = it.message
                },
                onFailure = {
                    _state.value = false
                    _message.value = it.message?:""
                }
            )
        }
    }
}