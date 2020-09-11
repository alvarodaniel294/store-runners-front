package com.example.storerunner.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.ItemCategoryAdapter
import com.example.storerunner.interfaces.ItemCategoryInterface
import com.example.storerunner.models.ItemCategory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ItemCategoryInterface {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemCategoryRecycler.layoutManager = LinearLayoutManager(context)
        itemCategoryRecycler.setHasFixedSize(true)
        homeViewModel.getAllCategories().observe(viewLifecycleOwner, Observer {
            initRecycler(it)
        })
        navController = Navigation.findNavController(view)
    }

    private fun initRecycler(mutableList: MutableList<ItemCategory>) {
        itemCategoryRecycler.adapter = ItemCategoryAdapter(mutableList, this)
        (itemCategoryRecycler.adapter as ItemCategoryAdapter).notifyDataSetChanged()
    }

    override fun goToItemsCategory(categoryId: Int) {
        val bundle = bundleOf("categoryId" to categoryId)
        navController.navigate(R.id.action_navigation_home_to_items_list, bundle)
    }
}