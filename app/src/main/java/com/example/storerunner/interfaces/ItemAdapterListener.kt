package com.example.storerunner.interfaces

import com.example.storerunner.models.Item

interface ItemAdapterListener {
    fun onTapAddToCart(item: Item)
}