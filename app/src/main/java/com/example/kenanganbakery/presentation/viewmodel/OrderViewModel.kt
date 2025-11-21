package com.example.kenanganbakery.presentation.viewmodel

import com.example.kenanganbakery.data.repository.MenuRepository

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.repository.BranchRepository
import com.example.kenanganbakery.data.repository.OrderRepository
import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.menu.Menu
import com.example.kenanganbakery.domain.models.order.Order
import com.example.kenanganbakery.domain.models.order.PostOrderRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderViewModel(application:Application):AndroidViewModel(application) {
    private val repository = OrderRepository(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders = _orders.asStateFlow()

    fun getAllOrders(
        category:String?=null,
        search:String?=null
    ){
        viewModelScope.launch {
            val result = repository.getAllOrders()
            result.fold(
                onSuccess = {
                    _orders.value = it.data
                },
                onFailure = {

                }
            )
        }
    }

    fun sendOrder(
        request:PostOrderRequest
    ){
        viewModelScope.launch {
            val result = repository.postOrder(request)
            result.fold(
                onSuccess = {
                    _state.value = true
                },
                onFailure = {
                    _state.value = false
                }
            )
        }
    }
}