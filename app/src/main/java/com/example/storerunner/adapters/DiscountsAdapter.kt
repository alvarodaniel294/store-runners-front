package com.example.storerunner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storerunner.R
import com.example.storerunner.models.Discount
import kotlinx.android.synthetic.main.container_discounts_item.view.*

class DiscountsAdapter(private val discountList: MutableList<Discount>) :
    RecyclerView.Adapter<DiscountsAdapter.DiscountViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        context = parent.context
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.container_discounts_item, parent, false)
        return DiscountViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        val item = discountList[position]
        holder.title.text = item.name
        holder.amount.text = item.discountPercent.toString() + "%"
        //Glide.with(context).load(item.name).into(holder.image)

    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    class DiscountViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.discountImageView
        val title: TextView = v.discountTitleTextView
        val amount: TextView = v.discountAmountTextView
    }
}