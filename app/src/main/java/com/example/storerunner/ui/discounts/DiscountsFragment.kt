package com.example.storerunner.ui.discounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.DiscountsAdapter
import com.example.storerunner.models.Discount
import kotlinx.android.synthetic.main.fragment_discounts.*

class DiscountsFragment : Fragment() {

    private lateinit var discountsViewModel: DiscountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discountsViewModel =
            ViewModelProviders.of(this).get(DiscountsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_discounts, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        discountsViewModel.getAllDiscounts().observe(viewLifecycleOwner, Observer {
            initRecycler(it)
        })
    }

    private fun initRecycler(mutableList: MutableList<Discount>) {
        recycler.adapter = DiscountsAdapter(mutableList)
        (recycler.adapter as DiscountsAdapter).notifyDataSetChanged()
    }
}