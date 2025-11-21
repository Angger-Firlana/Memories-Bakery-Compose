package com.example.kenanganbakery.presentation.viewmodel

import com.example.kenanganbakery.data.repository.MenuRepository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.repository.BranchRepository
import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.menu.Menu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel(application:Application):AndroidViewModel(application) {
    private val repository = MenuRepository(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _menus = MutableStateFlow<List<Menu>>(emptyList())
    val menus = _menus.asStateFlow()

    fun getAllMenu(
        category:String?=null,
        search:String?=null
    ){
        viewModelScope.launch {
            val result = repository.indexMenu(category, search)
            result.fold(
                onSuccess = {
                    _menus.value = it.data
                },
                onFailure = {
                    Log.e("ViewModelError", "Menu get error: ${it.message}")
                }
            )
        }
    }
}