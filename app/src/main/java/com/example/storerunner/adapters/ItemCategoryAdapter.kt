package com.example.storerunner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storerunner.Constants
import com.example.storerunner.R
import com.example.storerunner.interfaces.ItemCategoryInterface
import com.example.storerunner.models.ItemCategory
import kotlinx.android.synthetic.main.category_item.view.*

class ItemCategoryAdapter(
    private val itemCategoryList: MutableList<ItemCategory>,
    private var listener: ItemCategoryInterface
) :
    RecyclerView.Adapter<ItemCategoryAdapter.ItemCategoryViewHolder>() {

    class ItemCategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var categoryNameTextView: TextView = v.categoryNameTxt
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
        holder.categoryNumberTextView.text =
            holder.itemView.context.getString(R.string.category_number, item.categoryNumber)

        holder.itemView.setOnClickListener {
            listener.goToItemsCategory(item.categoryId)
        }

        Glide.with(holder.itemView.context)
            .load(Constants.IP_ADDRESS + item.webImage)
            .into(holder.categoryImageVew)
    }

}