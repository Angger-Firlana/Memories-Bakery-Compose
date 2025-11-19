package com.example.kenanganbakery.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.repository.AuthRepository
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application:Application):AndroidViewModel(application) {
    private val repository = AuthRepository(application.applicationContext)

    private val _state = MutableStateFlow<Boolean?>(null)
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun login(
        request:LoginRequest
    ){
        viewModelScope.launch {
            val result = repository.login(request)
            _isLoading.value = true
            result.fold(
                onSuccess = {
                    _state.value = true
                    Log.d("AuthViewModel", it.toString())
                },
                onFailure = {
                    _state.value = false
                    Log.e("AuthViewModel", it.toString())
                }
            )

            _isLoading.value = false

        }

    }

    fun clearState(){
        _state.value = null
    }
}