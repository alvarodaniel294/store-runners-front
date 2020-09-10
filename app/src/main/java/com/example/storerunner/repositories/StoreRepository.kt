package com.example.storerunner.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.storerunner.models.Item
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
                Log.d("daniel", "response successfull" )

                if (response.isSuccessful) {
                    Log.d("daniel", response.body().toString())
                    mItems.value = response.body()
                }
            }
        })
        return mItems
    }
}