package com.example.polimove.ui.routeDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.polimove.R

private const val ARG_PARAM1 = "routeName"

class RouteDetails : Fragment() {
    private var routeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            routeName = it.getString(ARG_PARAM1)
        }
        routeName = arguments?.getString(ARG_PARAM1)
        Log.println(Log.ASSERT, "1", routeName as String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            RouteDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}