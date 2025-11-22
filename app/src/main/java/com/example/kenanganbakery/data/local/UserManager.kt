package com.example.kenanganbakery.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences.*
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.*
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.*
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC
import com.example.kenanganbakery.domain.models.user.User
import com.google.gson.Gson
import javax.crypto.EncryptedPrivateKeyInfo

class UserManager(context:Context) {
    val gson = Gson()
    private val sharedPref = create(
        "user_preferences",
        MasterKeys.getOrCreate(AES256_GCM_SPEC),
        context,
        AES256_SIV,
        AES256_GCM
    )

    fun saveUser(user:User){
        val userStr = gson.toJson(user)
        sharedPref.edit{
            putString("user",userStr)
        }
    }

    fun getUser():User?{
        val userStr = sharedPref.getString("user",null)
        return gson.fromJson(userStr,User::class.java)
    }

    fun clearUser(){
        sharedPref.edit().remove("user").apply()
    }
}