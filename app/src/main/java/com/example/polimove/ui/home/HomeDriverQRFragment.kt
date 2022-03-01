package com.example.polimove.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.databinding.FragmentHomeBinding

class HomeDriverQRFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val QR: TextView = binding.textViewQRCODE
        homeViewModel.QRCODEDRIVER.observe(viewLifecycleOwner) {
            QR.text = it
        }
        val numberAsient:TextView = binding.textViewNumberAsiento
        homeViewModel.numberAsiento.observe(viewLifecycleOwner){
            numberAsient.text = it
        }
        val nameStudent:TextView=binding.textViewNameStd
        homeViewModel.nameStd.observe(viewLifecycleOwner){
            nameStudent.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}