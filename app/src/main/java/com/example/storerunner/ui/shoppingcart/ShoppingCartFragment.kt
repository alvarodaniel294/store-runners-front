package com.example.storerunner.ui.shoppingcart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.ShoppingCartAdapter
import com.example.storerunner.models.ItemCart
import com.example.storerunner.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_discounts.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : Fragment(), ShoppingCartAdapter.ShoppingCartAdapterListener {

    private lateinit var shoppingCartViewModel: ShoppingCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoppingCartViewModel =
            ViewModelProviders.of(this).get(ShoppingCartViewModel::class.java)
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopRecycler.layoutManager = LinearLayoutManager(context)
        shopRecycler.setHasFixedSize(true)
        observeList()
    }

    private fun observeList(){
        progressBar.visibility = View.VISIBLE
        shopRecycler.visibility = View.GONE
        shoppingCartViewModel.getAllShoppingCarts().observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                initRecycler(list)
            }
        })
    }

    private fun initRecycler(list: MutableList<ItemCart>) {
        progressBar.visibility = View.GONE
        shopRecycler.visibility = View.VISIBLE
        shopRecycler.adapter = ShoppingCartAdapter(list, this)
        (shopRecycler.adapter as ShoppingCartAdapter).notifyDataSetChanged()
    }

    override fun onRemoveQuantity(item: ItemCart) {

        item.itemQuantity = item.itemQuantity.toInt() - 1
        Log.d("daniel", item.toString())
        shoppingCartViewModel.updateItemCart(item).observe(viewLifecycleOwner, Observer {
            shoppingCartViewModel.getAllShoppingCarts().observe(viewLifecycleOwner, Observer {cartItems->
                initRecycler(cartItems)
            })
        })
    }

    override fun onAddQuantity(item: ItemCart) {


        item.itemQuantity = item.itemQuantity.toInt() + 1
        Log.d("daniel", item.toString())

        shoppingCartViewModel.updateItemCart(item).observe(viewLifecycleOwner, Observer {
            shoppingCartViewModel.getAllShoppingCarts().observe(viewLifecycleOwner, Observer {cartItems->
                initRecycler(cartItems)
            })
        })
    }
}