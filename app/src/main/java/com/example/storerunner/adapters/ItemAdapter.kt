package com.example.storerunner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var itemNameTextView: TextView = v.itemName
    }
}