package com.example.polimove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.polimove.databinding.ActivityMainBinding
import com.example.polimove.databinding.ActivityMainNoRouteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


class MainActivityNoRoute : AppCompatActivity() {


    private lateinit var binding: ActivityMainNoRouteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNoRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

          //Ir a la navegacion cuando un estudiante no ha registrado una ruta
          val navControllerNoRoute = findNavController(R.id.nav_host_fragment_activity_main_no_route)
          val appBarConfigurationNoRoute = AppBarConfiguration(
              setOf(
                  R.id.navigation_home, R.id.navigation_routes, R.id.navigation_profile
              )
          )

          setupActionBarWithNavController( navControllerNoRoute, appBarConfigurationNoRoute)
          navView.setupWithNavController( navControllerNoRoute)

    }
}