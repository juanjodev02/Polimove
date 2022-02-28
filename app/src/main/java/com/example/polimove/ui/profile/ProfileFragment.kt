package com.example.polimove.ui.profile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyCharacterMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.Service.UserService
import com.example.polimove.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    //INICILIZACIÓN DE VARIABLES
    private var cedula:String? = ""
    private lateinit var textViewName: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var buttonSignOut: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)*/

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewName = binding.textViewEstName
        /*profileViewModel.fullNameStd.observe(viewLifecycleOwner) {
            textViewNomCompletoEst.text = it
        }*/

        textViewEmail = binding.textViewEstEmail
        /*profileViewModel.emailStd.observe(viewLifecycleOwner) {
            textViewCorreoEst.text = it
        }*/

        buttonSignOut = binding.buttonContinuar
        buttonDelete = binding.buttonEliminarDatos
        /*profileViewModel.numberStd.observe(viewLifecycleOwner) {
            textViewCelularEst.text = it
        }*/

        parentFragmentManager.setFragmentResultListener("key",this, FragmentResultListener {
                Key, result ->
            cedula = result.getString("cedula")
            Log.d("CI", "La cédula: $cedula")
            UserService.getData(cedula as String) { nameUser ->
                textViewName.text = nameUser.name + " " + nameUser.lastName
                textViewEmail.text = nameUser.email
            }
        })

        buttonSignOut.setOnClickListener {
            UserService.signOff()
        }
        buttonDelete.setOnClickListener {
            UserService.getUserId(cedula)
            val activity: Activity? = activity
            Toast.makeText(activity, "Datos eliminados exitosamente.", Toast.LENGTH_LONG)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}