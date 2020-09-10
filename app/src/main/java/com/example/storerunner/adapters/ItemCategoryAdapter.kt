package com.example.storerunner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storerunner.R
import com.example.storerunner.models.ItemCategory
import kotlinx.android.synthetic.main.category_item.view.*

class ItemCategoryAdapter(private val itemCategoryList: MutableList<ItemCategory>) :
    RecyclerView.Adapter<ItemCategoryAdapter.ItemCategoryViewHolder>() {

    class ItemCategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var categoryNameTextView: TextView = v.categoryNameTxt
        var categoryDescriptionTextView: TextView = v.categoryDescriptionTxt
        var categoryNumberTextView: TextView = v.categoryNumberTxt
        var categoryImageVew: ImageView = v.categoryImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCategoryViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ItemCategoryViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return itemCategoryList.size
    }

    override fun onBindViewHolder(holder: ItemCategoryViewHolder, position: Int) {
        val item = itemCategoryList[position]
        holder.categoryNameTextView.text = item.name
        holder.categoryDescriptionTextView.text = item.description
        holder.categoryNumberTextView.text = item.categoryNumber
//        Glide.with(holder.itemView.context)
//            .load(item.webImage)
//            .into(holder.categoryImageVew)
    }

}