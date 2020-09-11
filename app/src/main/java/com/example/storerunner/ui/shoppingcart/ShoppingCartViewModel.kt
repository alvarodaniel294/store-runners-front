package com.example.storerunner.ui.shoppingcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.storerunner.models.ItemCart
import com.example.storerunner.repositories.StoreRepository

class ShoppingCartViewModel : ViewModel() {
    private var mStoreRepository: StoreRepository =
        StoreRepository.StoreRepositoryObject.getInstance()

    fun getAllShoppingCarts(): LiveData<MutableList<ItemCart>> {
        return mStoreRepository.getAllShoppingCarts()
    }

    fun updateItemCart(itemCart: ItemCart): LiveData<ItemCart> {
        return mStoreRepository.updateShoppingCart(itemCart)
    }

    fun deleteFromCart(itemId: Number): LiveData<Unit> {
        return mStoreRepository.deleteFromCart(itemId)
    }

    fun optimizeList(shoppingList: MutableList<ItemCart>): LiveData<MutableList<ItemCart>> {
        return mStoreRepository.optimizeRoute(shoppingList)
    }
}