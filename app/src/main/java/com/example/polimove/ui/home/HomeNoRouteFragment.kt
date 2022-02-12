package com.example.polimove.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.databinding.FragmentHomeNoRouteBinding

class HomeNoRouteFragment : Fragment() {

    private var _binding: FragmentHomeNoRouteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeNoRouteViewModel::class.java)

        _binding = FragmentHomeNoRouteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


    return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


