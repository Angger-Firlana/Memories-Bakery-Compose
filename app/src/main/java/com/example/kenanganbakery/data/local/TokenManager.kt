package com.example.kenanganbakery.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.*
import androidx.security.crypto.MasterKeys

@Suppress("DEPRECATION")
class TokenManager(context:Context) {
    private val sharedPreferences = create(
        "token_encrypted",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        PrefKeyEncryptionScheme.AES256_SIV,
        PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token:String){
        sharedPreferences.edit {
            putString("token", token)
        }
    }

    fun getToken():String?{
        val token = sharedPreferences.getString("token", null)
        return token
    }

    fun clearToken(){
        sharedPreferences.edit{
            remove("token")
        }
    }
}