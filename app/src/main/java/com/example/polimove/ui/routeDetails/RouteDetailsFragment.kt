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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.polimove.databinding.FragmentRouteDetailsBinding
import com.example.polimove.services.routes.RoutesService
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME
import com.google.firebase.firestore.FirebaseFirestore


private const val ROUTE_NAME_PARAM = "routeName"

class RouteDetails : Fragment() {
    private var _binding: FragmentRouteDetailsBinding? = null
    private val binding get() = _binding!!

    private var routeName: String? = ""
    private lateinit var routeId: String
    private lateinit var titleTextView: TextView
    private lateinit var stopsListView: ListView
    private lateinit var registerButton: Button
    private var cedula: String = ""

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
            Toast.makeText(activity, "No se tiene los datos necesarios para mostrar una ruta.", Toast.LENGTH_LONG).show()
        }

        val firestore = FirebaseFirestore.getInstance()

        RoutesService.getRouteByName(firestore, routeName as String) { route ->
            this.routeId = route.id.toString()
            stopsListView.adapter = ArrayAdapter(
                activity as Context,
                R.layout.simple_list_item_1,
                route.stops!!
            )

            val listadoLeido = this.readInformation()
            if(listadoLeido?.first != null){
                this.cedula = listadoLeido.first
                RoutesService.checkIfUserHasARouteAttached(firestore,listadoLeido.first) { hasRouteAttached ->
                    if (hasRouteAttached) {
                        this.registerButton.visibility = View.GONE
                    } else {
                        this.registerButton.visibility = View.VISIBLE
                    }
                    registerButton.setOnClickListener {
                        this.onClickRegisterButton()
                    }
                }
            }
        }

        val root: View = binding.root
        titleTextView = binding.routeDetailsText
        titleTextView.text = routeName
        return root
    }

    fun readInformation():Pair<String,String>{
        val sharedPref = context?.getSharedPreferences(SHAREDINFO_FILENAME.toString(),0)
        val cedula = sharedPref?.getString(LOGIN_KEY,"").toString()
        val clave = sharedPref?.getString(PASSWORD_KEY,"").toString()
        return (cedula to clave)
    }

    private fun onClickRegisterButton () {
        val firestore = FirebaseFirestore.getInstance()
        RoutesService.attachUserToRoute(firestore, this.routeId, this.cedula) { user ->
            findNavController().navigate(com.example.polimove.R.id.action_routeDetails_to_navigation_home)
        }
    }
}