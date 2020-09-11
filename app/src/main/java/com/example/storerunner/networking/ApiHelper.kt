package com.example.storerunner.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    private var instance: StoreApi? = null
//    private val baseURL = "http://54.83.188.108:9090/api/"
    private val baseURL = "http://3.90.249.235:9090/api/"
    fun getApi(): StoreApi {
        if (instance != null) {
            return instance as StoreApi
        } else {
            val okHttpClient = OkHttpClient().newBuilder()
                .build()
            instance = Retrofit
                .Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StoreApi::class.java)
            return instance as StoreApi
        }
    }
}