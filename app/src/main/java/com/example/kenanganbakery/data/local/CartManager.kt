package com.example.kenanganbakery.data.local

import android.content.Context
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.CartItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartManager(context: Context) {
    private val sharedPref =
        context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveCart(cart: List<CartItem>) {
        val json = gson.toJson(cart)
        sharedPref.edit().putString("cart", json).apply()
    }

    fun getCart(): List<CartItem>? {
        val json = sharedPref.getString("cart", null) ?: return null
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearCart() {
        sharedPref.edit().remove("cart").apply()
    }
}
