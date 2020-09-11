package com.example.storerunner.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.ItemAdapter
import com.example.storerunner.adapters.ItemCategoryAdapter
import com.example.storerunner.interfaces.ItemAdapterListener
import com.example.storerunner.models.Item
import com.example.storerunner.models.ItemToAddToCart
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment(), ItemAdapterListener {

    private lateinit var itemsViewModel: ItemsViewModel
    var categoryId: Number? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemsViewModel =
            ViewModelProviders.of(this).get(ItemsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_items, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemRecycler.layoutManager = LinearLayoutManager(context)
        itemRecycler.setHasFixedSize(true)
        arguments?.getInt("categoryId")?.let { lifecycle ->
            categoryId = lifecycle
            itemsViewModel.getItemsByCategoryId(lifecycle)
                .observe(viewLifecycleOwner, Observer {
                    initRecycler(it)
                })
        }
    }

    private fun initRecycler(mutableList: MutableList<Item>) {
        itemRecycler.adapter = ItemAdapter(mutableList, this)
        (itemRecycler.adapter as ItemAdapter).notifyDataSetChanged()
    }

    override fun onTapAddToCart(item: Item) {
        val itemToAddToCart = ItemToAddToCart(
            0,
            item.description,
            item.itemNumber,
            1,
            item.name,
            item.posX,
            item.posY,
            item.position,
            item.price,
            item.item_categoryId,
            item.webImage
        )
        itemsViewModel.addItemToCart(itemToAddToCart).observe(viewLifecycleOwner, Observer {
            arguments?.getInt("categoryId")?.let { lifecycle ->
                categoryId = lifecycle
                itemsViewModel.getItemsByCategoryId(lifecycle)
                    .observe(viewLifecycleOwner, Observer {
                        initRecycler(it)
                    })
                Toast.makeText(context, "1 Item added to your Cart", Toast.LENGTH_SHORT).show()
            }
        })
    }
}