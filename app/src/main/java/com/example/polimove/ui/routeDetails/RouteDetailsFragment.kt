package com.example.polimove.ui.routeDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.polimove.databinding.FragmentRouteDetailsBinding

private const val ROUTE_NAME_PARAM = "routeName"

class RouteDetails : Fragment() {
    private var _binding: FragmentRouteDetailsBinding? = null
    private val binding get() = _binding!!

    private var routeName: String? = ""
    private lateinit var titleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRouteDetailsBinding.inflate(inflater, container, false)
        routeName = arguments?.getString(ROUTE_NAME_PARAM)
        Log.println(Log.ASSERT, "1", routeName as String)
        val root: View = binding.root
        titleTextView = binding.routeDetailsText
        titleTextView.text = routeName
        return root
    }
}