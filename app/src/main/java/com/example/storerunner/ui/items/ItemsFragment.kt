package com.example.storerunner.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.ItemAdapter
import com.example.storerunner.adapters.ItemCategoryAdapter
import com.example.storerunner.models.Item
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment() {

    private lateinit var itemsViewModel: ItemsViewModel

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
            itemsViewModel.getItemsByCategoryId(lifecycle)
                .observe(viewLifecycleOwner, Observer {
                    initRecycler(it)
                })
        }
    }

    private fun initRecycler(mutableList: MutableList<Item>) {
        itemRecycler.adapter = ItemAdapter(mutableList)
        (itemRecycler.adapter as ItemAdapter).notifyDataSetChanged()
    }
}