package com.example.storerunner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storerunner.R
import com.example.storerunner.models.Item
import kotlinx.android.synthetic.main.container_item.view.*

class ItemAdapter(private val itemList: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.container_item, parent, false)
        return ItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemNameTextView.text = item.name
        holder.itemNumberTextView.text = holder.itemView.context.getString(R.string.item_number, item.itemNumber)
        holder.itemPriceTextView.text =
            holder.itemView.context.getString(R.string.price, item.price)
        holder.itemQuantityTextView.text =
            holder.itemView.context.getString(R.string.stock, item.itemQuantity)

        Glide.with(holder.itemView.context)
            .load("http://3.90.249.235" + item.webImage)
            .into(holder.itemImageView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var itemNameTextView: TextView = v.itemNameTxt
        var itemPriceTextView: TextView = v.itemPriceTxt
        var itemNumberTextView: TextView = v.itemNumberTxt
        var itemQuantityTextView: TextView = v.itemQuantityTxt
        var itemImageView: ImageView = v.itemImageView

    }
}