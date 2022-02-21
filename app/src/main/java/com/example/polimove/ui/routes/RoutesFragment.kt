package com.example.polimove.ui.routes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.polimove.R
import com.example.polimove.databinding.FragmentRoutesBinding
import com.example.polimove.services.routes.RoutesService

class RoutesFragment : Fragment() {

    private var _binding: FragmentRoutesBinding? = null
    private val binding get() = _binding!!

    private lateinit var routesList: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        routesList = binding.routesListView;

        RoutesService.getAllRoutes { routes ->
            routesList.adapter = ArrayAdapter(
                activity as Context,
                android.R.layout.simple_list_item_1,
                routes.map { route -> route.name }
            )
            routesList.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val selectedRouteName = parent.getItemAtPosition(position)
                    val paramsBundle = bundleOf("routeName" to selectedRouteName)
                    findNavController().navigate(R.id.action_navigation_routes_to_routeDetails, paramsBundle)
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}