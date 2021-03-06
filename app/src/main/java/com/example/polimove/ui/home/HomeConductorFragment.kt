package com.example.polimove.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.databinding.FragmentHomeConductorBinding


class HomeConductorFragment : Fragment() {

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}