package com.example.storerunner.models

data class ItemCart(
    var cartId: Int,
    var shop_categoryId: Number,
    var description: String,
    var itemNumber: String,
    var itemQuantity: Number,
    var name: String,
    var posX: Double,
    var posY: Double,
    var position: Number,
    var price: Number,
    var webImage: String
) {
}