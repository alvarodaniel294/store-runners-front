package com.example.storerunner.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storerunner.R
import com.example.storerunner.adapters.ItemAdapter
import com.example.storerunner.models.Item
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)


        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {

        })


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        notificationsViewModel.getAllItems().observe(viewLifecycleOwner, Observer {
            initRecycler(it)
        })
    }

    private fun initRecycler(mutableList: MutableList<Item>) {
        recycler.adapter = ItemAdapter(mutableList)
        (recycler.adapter as ItemAdapter).notifyDataSetChanged()
    }
}