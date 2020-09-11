package com.example.storerunner.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.storerunner.R
import com.peterlaurence.mapview.MapView
import com.peterlaurence.mapview.MapViewConfiguration
import com.peterlaurence.mapview.core.TileStreamProvider
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.io.InputStream

class DashboardFragment : Fragment() {
    private lateinit var parentView: ViewGroup
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        root.also {
            parentView = it.mapContainer as ViewGroup
            context?.let {ctx->
                makeMapView(ctx)?.addToFragment()
            }
        }
        return root
    }

    private fun MapView.addToFragment() {
        id = R.id.mapview_id
        isSaveEnabled = true

        parentView.addView(this, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }


    private fun makeMapView(context: Context): MapView? {
        val tileStreamProvider = object : TileStreamProvider {
            override fun getTileStream(row: Int, col: Int, zoomLvl: Int): InputStream? {
                return try {
                    context.assets?.open("tiles/storemap/$zoomLvl/$row/$col.jpg")
                } catch (e: Exception) {
                    null
                }
            }
        }
        val tileSize = 256
        val config = MapViewConfiguration(
            5, 8192, 8192, tileSize, tileStreamProvider
        ).setMaxScale(2f)

        return MapView(context).apply {
            configure(config)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cart_menu ->{
                openShoppingCart()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openShoppingCart() {
        navController.navigate(R.id.action_navigation_dashboard_to_shoppingCartFragment)
    }
}