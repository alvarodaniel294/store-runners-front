package com.example.storerunner.ui.discounts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.DiscountsAdapter
import com.example.storerunner.models.Discount
import com.example.storerunner.models.Item
import com.example.storerunner.ui.shoppingcart.ShoppingCartViewModel
import kotlinx.android.synthetic.main.fragment_discounts.*

class DiscountsFragment : Fragment() {

    private lateinit var discountsViewModel: DiscountsViewModel
    private lateinit var shoppingCartViewModel: ShoppingCartViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        discountsViewModel =
            ViewModelProviders.of(this).get(DiscountsViewModel::class.java)
        shoppingCartViewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_discounts, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)

        val finalList: MutableList<Discount> = mutableListOf()
        val mItems: MutableLiveData<MutableList<Discount>> = MutableLiveData()

        discountsViewModel.getAllDiscounts().observe(viewLifecycleOwner, Observer { discounts ->
            shoppingCartViewModel.getAllShoppingCarts()
                .observe(viewLifecycleOwner, Observer { items ->
                    items.forEach { item ->
                        discounts.forEach { discount ->
                            if (item.cartId == discount.itemId_Items) {
                                finalList.add(discount)
                            }
                        }
                    }
                    mItems.value = finalList
                })
        })

        mItems.observe(viewLifecycleOwner, Observer {
            initRecycler(it)
        })
    }

    private fun initRecycler(mutableList: MutableList<Discount>) {
        recycler.adapter = DiscountsAdapter(mutableList)
        (recycler.adapter as DiscountsAdapter).notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart_menu -> {
                openShoppingCart()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openShoppingCart() {
        navController.navigate(R.id.action_navigation_notifications_to_shoppingCartFragment)
    }
}