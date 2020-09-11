package com.example.storerunner.models

data class Discount(
    val description: String?,
    val discountId: Number,
    val discountNumber: String,
    val discountPercent: Number,
    val name: String,
    val itemId_Items: Int,
    val webImage: String
) {
}