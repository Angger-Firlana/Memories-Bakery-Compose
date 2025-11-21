package com.example.kenanganbakery.presentation.viewmodel

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

class BranchViewModel(application:Application):AndroidViewModel(application) {
    private val repository = BranchRepository(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _branchs = MutableStateFlow<List<Branch>>(emptyList())
    val branchs = _branchs.asStateFlow()

    fun getAllBranch(
        category:String?=null,
        search:String?=null
    ){
        viewModelScope.launch {
            val result = repository.indexBranch(category, search)
            result.fold(
                onSuccess = {
                    _branchs.value = it.data
                },
                onFailure = {
                    Log.e("ViewModelError", "Branch get error: ${it.message}")
                }
            )
        }
    }
}