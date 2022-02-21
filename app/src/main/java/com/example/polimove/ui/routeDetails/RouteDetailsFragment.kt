package com.example.polimove.ui.routeDetails

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.polimove.databinding.FragmentRouteDetailsBinding
import com.example.polimove.services.routes.RoutesService


private const val ROUTE_NAME_PARAM = "routeName"

class RouteDetails : Fragment() {
    private var _binding: FragmentRouteDetailsBinding? = null
    private val binding get() = _binding!!

    private var routeName: String? = ""
    private lateinit var titleTextView: TextView
    private lateinit var stopsListView: ListView
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRouteDetailsBinding.inflate(inflater, container, false)
        routeName = arguments?.getString(ROUTE_NAME_PARAM)
        stopsListView = binding.stopsListView
        registerButton = binding.button

        if (routeName == null) {
            val activity: Activity? = activity
            Toast.makeText(activity, "No se tiene los datos necesarios para mostrar una ruta.", Toast.LENGTH_LONG)
        }

        RoutesService.getRouteByName(routeName as String) { route ->
            Log.println(Log.ASSERT, "1", route.name as String)
            stopsListView.adapter = ArrayAdapter(
                activity as Context,
                R.layout.simple_list_item_1,
                route.stops!!
            )
        }

        registerButton.setOnClickListener {
            this.onClickRegisterButton()
        }

        val root: View = binding.root
        titleTextView = binding.routeDetailsText
        titleTextView.text = routeName
        return root
    }

    fun onClickRegisterButton () {

    }
}