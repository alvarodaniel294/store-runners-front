package com.example.storerunner.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.storerunner.models.*
import com.example.storerunner.networking.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository {
    object StoreRepositoryObject {
        var myInstance: StoreRepository? = null
        fun getInstance(): StoreRepository {
            return if (myInstance != null) {
                myInstance as StoreRepository
            } else {
                StoreRepository()
            }
        }
    }

    fun getAllItems(): MutableLiveData<MutableList<Item>> {
        val mItems: MutableLiveData<MutableList<Item>> = MutableLiveData()
        ApiHelper.getApi().getAllItems().enqueue(object : Callback<MutableList<Item>> {
            override fun onFailure(call: Call<MutableList<Item>>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<Item>>,
                response: Response<MutableList<Item>>
            ) {
                Log.d("daniel", "response successfull")

                if (response.isSuccessful) {
                    Log.d("daniel", response.body().toString())
                    mItems.value = response.body()
                }
            }
        })
        return mItems
    }

    fun getAllCategories(): MutableLiveData<MutableList<ItemCategory>> {
        val mCategories: MutableLiveData<MutableList<ItemCategory>> = MutableLiveData()
        ApiHelper.getApi().getAllCategories().enqueue(object : Callback<MutableList<ItemCategory>> {
            override fun onFailure(call: Call<MutableList<ItemCategory>>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<ItemCategory>>,
                response: Response<MutableList<ItemCategory>>
            ) {
                Log.d("daniel", "response successfull")

                if (response.isSuccessful) {
                    Log.d("daniel", response.body().toString())
                    mCategories.value = response.body()
                }
            }

        }
        )
        return mCategories
    }

    fun getAllDiscounts(): MutableLiveData<MutableList<Discount>> {
        val mDiscounts: MutableLiveData<MutableList<Discount>> = MutableLiveData()
        ApiHelper.getApi().getAllDiscounts().enqueue(object : Callback<MutableList<Discount>> {
            override fun onFailure(call: Call<MutableList<Discount>>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<Discount>>,
                response: Response<MutableList<Discount>>
            ) {
                if (response.isSuccessful) {
                    mDiscounts.value = response.body()
                }
            }
        })
        return mDiscounts
    }

    fun getItemsByCategory(categoryId: Int): MutableLiveData<MutableList<Item>> {
        val mItems: MutableLiveData<MutableList<Item>> = MutableLiveData()
        ApiHelper.getApi().getItemsByCategoryId(categoryId)
            .enqueue(object : Callback<MutableList<Item>> {
                override fun onFailure(call: Call<MutableList<Item>>, t: Throwable) {
                    Log.d("daniel", "failed request")
                    Log.d("daniel", t.message.toString())
                }

                override fun onResponse(
                    call: Call<MutableList<Item>>,
                    response: Response<MutableList<Item>>
                ) {
                    if (response.isSuccessful) {
                        mItems.value = response.body()
                    }
                }

            })

        return mItems
    }

    fun getAllShoppingCarts(): MutableLiveData<MutableList<ItemCart>> {
        Log.d("daniel", "GETALL SHOPPING CARTS")
        val mShoppingCarts: MutableLiveData<MutableList<ItemCart>> = MutableLiveData()
        ApiHelper.getApi().getAllShoppingCarts().enqueue(object : Callback<MutableList<ItemCart>> {
            override fun onFailure(call: Call<MutableList<ItemCart>>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<ItemCart>>,
                response: Response<MutableList<ItemCart>>
            ) {
                if (response.isSuccessful) {
                    mShoppingCarts.value = response.body()
                }
            }
        })
        return mShoppingCarts
    }

    fun updateShoppingCart(itemCart: ItemCart): MutableLiveData<ItemCart> {
        val mShoppingCart: MutableLiveData<ItemCart> = MutableLiveData()
        ApiHelper.getApi().updateShoppingCart(itemCart).enqueue(object : Callback<ItemCart> {
            override fun onFailure(call: Call<ItemCart>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(call: Call<ItemCart>, response: Response<ItemCart>) {
                if (response.isSuccessful) {
                    mShoppingCart.value = response.body()
                }
            }
        })
        return mShoppingCart
    }

    fun addItemToCart(itemToAddToCart: ItemToAddToCart): MutableLiveData<ItemToAddToCart> {
        val mItemToAddToCart: MutableLiveData<ItemToAddToCart> = MutableLiveData()
        ApiHelper.getApi().addItemToCart(itemToAddToCart)
            .enqueue(object : Callback<ItemToAddToCart> {
                override fun onFailure(call: Call<ItemToAddToCart>, t: Throwable) {
                    Log.d("daniel", "failed request")
                    Log.d("daniel", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ItemToAddToCart>,
                    response: Response<ItemToAddToCart>
                ) {
                    if (response.isSuccessful) {
                        mItemToAddToCart.value = response.body()
                    }
                }
            })
        return mItemToAddToCart
    }

    fun deleteFromCart(itemId: Number): MutableLiveData<Unit> {
        val mVoid: MutableLiveData<Unit> = MutableLiveData()
        ApiHelper.getApi().deleteFromShoppingCart(itemId).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("daniel", "failed request")
                Log.d("daniel", t.message.toString())
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    mVoid.value = response.body()
                }
            }
        })
        return mVoid
    }
}