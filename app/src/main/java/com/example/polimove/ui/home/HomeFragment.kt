package com.example.polimove.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

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

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textViewEmoji: TextView = binding.textViewEmoji
        homeViewModel.emoji.observe(viewLifecycleOwner){
            textViewEmoji.text = it
        }

        val textViewTitulo: TextView = binding.textViewNombreRuta
        homeViewModel.nombreRuta.observe(viewLifecycleOwner){
            textViewTitulo.text = it
        }

        val textViewCode: TextView = binding.textViewCode
        homeViewModel.codigo.observe(viewLifecycleOwner){
            textViewCode.text = it
        }

        val textViewQRCODE: TextView = binding.textViewQRCODE
        homeViewModel.QRCODE.observe(viewLifecycleOwner){
            textViewQRCODE.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}