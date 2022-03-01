package com.example.polimove.ui.home

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.databinding.FragmentHomeNoRouteBinding
import com.example.polimove.services.user.UserService
import com.example.polimove.ui.routes.RoutesFragment

private const val USER_CI_PARAM = "1722951165"
class HomeNoRouteFragment : Fragment() {

    private var _binding: FragmentHomeNoRouteBinding? = null

    private val binding get() = _binding!!
    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var buttonReservarRuta: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeNoRouteBinding.inflate(inflater, container, false)

        cedula="1722951165"

        val root: View = binding.root
        nameTextView = binding.textHome
        buttonReservarRuta = binding.buttonReservarRuta


        UserService.getData(cedula as String) { nameUser ->
            nameTextView.text =
                "¡Hola! " + String(Character.toChars(0x1F44B)) + " " + nameUser.name + " " + nameUser.lastName
        }

    return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonReservarRuta.setOnClickListener{
            findNavController().navigate(R.id.action_Home_no_route_to_Routes)
               }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


