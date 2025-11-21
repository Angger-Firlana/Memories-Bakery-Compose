package com.example.kenanganbakery.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.*
import androidx.security.crypto.MasterKeys

@Suppress("DEPRECATION")
class WelcomeManager(context: Context) {
    private val sharedPref = create(
        "welcome_encrypted",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        PrefKeyEncryptionScheme.AES256_SIV,
        PrefValueEncryptionScheme.AES256_GCM
    )

    fun setStateWelcome(bool:Boolean){
        sharedPref.edit().putBoolean("welcome-state", bool).apply()
    }

    fun getStateWelcome():Boolean{
        return sharedPref.getBoolean("welcome-state", true)
    }
}