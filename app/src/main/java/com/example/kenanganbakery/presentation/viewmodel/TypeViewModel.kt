package com.example.kenanganbakery.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.repository.TypeRepository
import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.type.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TypeViewModel(application:Application): AndroidViewModel(application) {
    private val repository = TypeRepository(application)

    private val _types = MutableStateFlow<List<Type>>(emptyList())
    val types = _types.asStateFlow()

    fun getAllType(){
        viewModelScope.launch {
            val result = repository.getAllType()
            result.fold(
                onSuccess = {
                    _types.value = it.data
                },
                onFailure = {

                }
            )
        }
    }
}