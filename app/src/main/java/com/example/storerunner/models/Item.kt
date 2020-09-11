package com.example.storerunner.models

data class Item(
    var description: String,
    var itemId: Number,
    var itemNumber: String,
    var itemQuantity: Number,
    var name: String,
    var price: Number,
    var webImage: String,
    var posX: Double,
    var posY: Double,
    var position: Number,
    var item_categoryId: Number,
    var discountses: List<Discount>
) {
}