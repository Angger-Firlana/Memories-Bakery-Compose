package com.example.kenanganbakery.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.kenanganbakery.domain.models.branch.Branch
import com.google.gson.Gson

class BranchManager(context: Context) {
    private val sharedPref = EncryptedSharedPreferences.create(
        "branch_preferences",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setBranch(branch:Branch?){
        val branchJson = Gson().toJson(branch)
        sharedPref.edit().putString("branch", branchJson).apply()

    }

    fun getBranch():Branch?{
        val branchJson = sharedPref.getString("branch", null)
        return if (branchJson != null) {
            Gson().fromJson(branchJson, Branch::class.java)
        } else {
            null
        }
    }

    fun clearBranch(){
        sharedPref.edit().remove("branch").apply()
    }
}