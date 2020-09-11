package com.example.storerunner.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.storerunner.models.Item
import com.example.storerunner.models.ItemToAddToCart
import com.example.storerunner.repositories.StoreRepository

class ItemsViewModel : ViewModel() {

    private var mStoreRepository: StoreRepository =
        StoreRepository.StoreRepositoryObject.getInstance()

    fun getItemsByCategoryId(categoryId: Int): LiveData<MutableList<Item>> {
        return mStoreRepository.getItemsByCategory(categoryId)
    }

    fun addItemToCart(itemToAddToCart: ItemToAddToCart): LiveData<ItemToAddToCart> {
        return mStoreRepository.addItemToCart(itemToAddToCart)
    }

}