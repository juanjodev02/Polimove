package com.example.polimove.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeConductorBinding


class HomeConductorFragment : Fragment() {
    private lateinit var buttonAgregarPasajaero: Button

    private var _binding: FragmentHomeConductorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val conductorViewModel =
            ViewModelProvider(this).get(HomeConductorViewModel::class.java)

        _binding = FragmentHomeConductorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAgregarPasajaero.setOnClickListener{
            findNavController().navigate(R.id.action_home_conductor_to_home_driverqr)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}