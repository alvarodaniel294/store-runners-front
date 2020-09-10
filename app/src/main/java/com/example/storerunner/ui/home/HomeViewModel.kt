package com.example.storerunner.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storerunner.models.ItemCategory
import com.example.storerunner.repositories.StoreRepository

class HomeViewModel : ViewModel() {

    private var mStoreRepository: StoreRepository =
        StoreRepository.StoreRepositoryObject.getInstance()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllCategories(): LiveData<MutableList<ItemCategory>> {
        return mStoreRepository.getAllCategories()
    }
}