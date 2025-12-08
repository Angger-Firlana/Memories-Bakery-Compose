package com.example.kenanganbakery.presentation.viewmodel

import com.example.kenanganbakery.data.repository.MenuRepository

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
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

    private val _menu = MutableStateFlow<Menu?>(null)
    val menu = _menu.asStateFlow()

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

    fun getMenu(id:Int){
        viewModelScope.launch {
            val result = repository.getMenu(id)
            result.fold(
                onSuccess = {
                    _menu.value = it.data
                },
                onFailure = {
                    _menu.value = null
                    Log.e("ViewModelError", "Menu get error: ${it.message}")
                }
            )
        }
    }

    fun getMenuImage(url:String, callBack:(Bitmap?)-> Unit){
        viewModelScope.launch {
            val result = repository.getMenuImage(url = url)

            result.fold(
                onSuccess = { bitmap->
                    callBack(bitmap)
                },
                onFailure = {
                    callBack(null)
                    Log.e("ViewModelError", "Menu get error: ${it.message}")
                }
            )
        }
    }
}