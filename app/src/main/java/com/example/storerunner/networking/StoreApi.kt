package com.example.storerunner.networking

import androidx.lifecycle.MutableLiveData
import com.example.storerunner.models.Discount
import com.example.storerunner.models.Item
import com.example.storerunner.models.ItemCart
import com.example.storerunner.models.ItemCategory
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("shoppingCar/findAll")
    fun getAllShoppingCarts():Call<MutableList<ItemCart>>

    @PUT("shoppingCar/update")
    fun updateShoppingCart(@Body itemCart: ItemCart):Call<ItemCart>
}