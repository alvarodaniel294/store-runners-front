package com.example.storerunner.networking

import androidx.lifecycle.MutableLiveData
import com.example.storerunner.models.Discount
import com.example.storerunner.models.Item
import com.example.storerunner.models.ItemCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET("items/findAll")
    fun getAllItems(): Call<MutableList<Item>>

    @GET("categories/findAll")
    fun getAllCategories(): Call<MutableList<ItemCategory>>

    @GET("discounts/findAll")
    fun getAllDiscounts(): Call<MutableList<Discount>>

    @GET("items/findItemsByCategoryId/{categoryId}")
    fun getItemsByCategoryId(@Path("categoryId") categoryId: Int): Call<MutableList<Item>>
}