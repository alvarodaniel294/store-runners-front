package com.example.storerunner.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storerunner.models.Item
import com.example.storerunner.repositories.StoreRepository

class NotificationsViewModel : ViewModel() {

    private var mStoreRepository: StoreRepository =
        StoreRepository.StoreRepositoryObject.getInstance()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllItems(): LiveData<MutableList<Item>> {
        return mStoreRepository.getAllItems()
    }
}