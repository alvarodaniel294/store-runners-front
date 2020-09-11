package com.example.storerunner.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storerunner.Constants
import com.example.storerunner.R
import com.example.storerunner.models.ItemCart
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.container_cart_item.view.*

class ShoppingCartAdapter(
    var shoppingCartList: MutableList<ItemCart>,
    var listener: ShoppingCartAdapterListener
) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    lateinit var context: Context

    interface ShoppingCartAdapterListener {
        fun onRemoveQuantity(item: ItemCart)
        fun onAddQuantity(item: ItemCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        context = parent.context
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.container_cart_item, parent, false)
        return ShoppingCartViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        val item = shoppingCartList[position]
        holder.title.text = item.name
        holder.description.text = item.description
        holder.quantity.text = item.itemQuantity.toString()
        holder.buttonAdd.setOnClickListener {
            listener.onAddQuantity(item)
        }
        holder.buttonRemove.setOnClickListener {
            listener.onRemoveQuantity(item)
        }
        Glide.with(context).load(Constants.IP_ADDRESS + item.webImage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return shoppingCartList.size
    }

    class ShoppingCartViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.cartItemTitleTextView
        val description: TextView = v.cartItemDescriptionTextView
        val buttonAdd: MaterialButton = v.addItemButton
        val buttonRemove: MaterialButton = v.removeItemButton
        val quantity: TextView = v.currentItemQuantityTextView
        val image:ImageView = v.cartItemImageView
    }
}