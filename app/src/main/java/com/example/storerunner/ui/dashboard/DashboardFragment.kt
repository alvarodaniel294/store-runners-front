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
import com.example.storerunner.models.ItemCart
import com.peterlaurence.mapview.MapView
import com.peterlaurence.mapview.MapViewConfiguration
import com.peterlaurence.mapview.api.addCallout
import com.peterlaurence.mapview.api.addMarker
import com.peterlaurence.mapview.api.setMarkerTapListener
import com.peterlaurence.mapview.core.TileStreamProvider
import com.peterlaurence.mapview.demo.fragments.views.MapMarker
import com.peterlaurence.mapview.demo.fragments.views.MarkerCallout
import com.peterlaurence.mapview.markers.MarkerTapListener
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
        root.also {
            parentView = it.mapContainer as ViewGroup

            dashboardViewModel.getAllShoppingCarts().observe(viewLifecycleOwner, Observer {
                context?.let {ctx->
                    makeMapView(ctx, it)?.addToFragment()
                }
            })
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


    private fun makeMapView(context: Context, cartList: MutableList<ItemCart>): MapView? {
        val mapView = MapView(context)
        val tileStreamProvider = object : TileStreamProvider {
            override fun getTileStream(row: Int, col: Int, zoomLvl: Int): InputStream? {
                return try {
                    context.assets?.open("tiles/storemap/$zoomLvl/$row/$col.jpg")
                } catch (e: Exception) {
                    null
                }
            }
        }
//        val tileSize = 256
//        val config = MapViewConfiguration(
//            5, 8192, 8192, tileSize, tileStreamProvider
//        ).setMaxScale(2f)

//        return MapView(context).apply {
//            configure(config)
//        }
        val tileSize = 256
        val config = MapViewConfiguration(
            5, 8192, 8192, tileSize, tileStreamProvider
        ).setMaxScale(2f).setPadding(tileSize * 2)

        mapView.configure(config)
        mapView.defineBounds(0.0, 0.0, 1.0, 1.0)

        for (cartItem in cartList){
            mapView.addNewMarker(cartItem.posX, cartItem.posY, cartItem.name, cartItem.itemQuantity)
        }

        mapView.setMarkerTapListener(object : MarkerTapListener {
            override fun onMarkerTap(view: View, x: Int, y: Int) {
                if (view is MapMarker) {
                    val callout = MarkerCallout(context)
                    callout.setTitle(view.name)
                    callout.setSubTitle(view.quantity.toString() + " item(s) to buy")
                    mapView.addCallout(callout, view.x, view.y, -0.5f, -1.2f, 0f, 0f)
                    callout.transitionIn()
                }
            }
        })
        return mapView

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

    private fun MapView.addNewMarker(x: Double, y: Double, name: String, quantity:Number) {
        val marker = MapMarker(context, x, y, name, quantity).apply {
            setImageResource(R.drawable.map_marker)
        }

        addMarker(marker, x, y)
    }

    private fun openShoppingCart() {
        navController.navigate(R.id.action_navigation_dashboard_to_shoppingCartFragment)
    }
}