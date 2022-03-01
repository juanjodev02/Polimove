package com.example.polimove.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.databinding.FragmentProfileBinding

class ProfileDriverFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileDriveViewModel =
            ViewModelProvider(this).get(ProfileDriverViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewNomCompletoEst: TextView = binding.textViewEstName
        profileDriveViewModel.fullNameDriver.observe(viewLifecycleOwner) {
            textViewNomCompletoEst.text = it
        }

        val textViewCorreoEst: TextView = binding.textViewEstEmail
        profileDriveViewModel.emailDriver.observe(viewLifecycleOwner) {
            textViewCorreoEst.text = it
        }

        val textViewCelularEst: TextView = binding.textViewEstTelefono
        profileDriveViewModel.numberDriver.observe(viewLifecycleOwner) {
            textViewCelularEst.text = it
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

