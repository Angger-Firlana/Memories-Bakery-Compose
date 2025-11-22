package com.example.kenanganbakery.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kenanganbakery.data.local.TokenManager
import com.example.kenanganbakery.data.local.UserManager
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
        request:LoginRequest,
        context:Context
    ){
        viewModelScope.launch {
            val userManager = UserManager(context)
            val tokenManager = TokenManager(context)
            val result = repository.login(request)
            _isLoading.value = true
            result.fold(
                onSuccess = { body ->
                    userManager.saveUser(body.user)
                    tokenManager.saveToken(body.token)
                    _state.value = true
                },
                onFailure = {
                    _state.value = false
                }
            )

            _isLoading.value = false

        }

    }

    fun clearState(){
        _state.value = null
    }
}