package com.example.storerunner.networking

import androidx.lifecycle.MutableLiveData
import com.example.storerunner.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

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
    fun getAllShoppingCarts(): Call<MutableList<ItemCart>>

    @PUT("shoppingCar/update")
    fun updateShoppingCart(@Body itemCart: ItemCart): Call<ItemCart>

    @POST("shoppingCar/save")
    fun addItemToCart(@Body itemToSave: ItemToAddToCart): Call<ItemToAddToCart>

    @POST("shoppingCar/optimize")
    fun optimizeList(@Body shoppingList: MutableList<ItemCart>): Call<MutableList<ItemCart>>

    @DELETE("shoppingCar/delete/{cartId}")
    fun deleteFromShoppingCart(@Path("cartId") cartId: Number): Call<Unit>
}