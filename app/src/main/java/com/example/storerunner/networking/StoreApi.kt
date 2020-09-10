package com.example.storerunner.networking

import com.example.storerunner.models.Discount
import com.example.storerunner.models.Item
import retrofit2.Call
import retrofit2.http.GET

interface StoreApi {

    @GET("items/findAll")
    fun getAllItems(): Call<MutableList<Item>>

    @GET("discounts/findAll")
    fun getAllDiscounts():Call<MutableList<Discount>>
}