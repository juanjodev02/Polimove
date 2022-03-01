package com.example.polimove.ui.profile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.polimove.databinding.FragmentProfileDriverBinding
import com.example.polimove.services.routes.UserService

class ProfileDriverFragment : Fragment() {
    private var _binding: FragmentProfileDriverBinding? = null

    private val binding get() = _binding!!

    //INICIALIZACIÓN DE VARIABLES
    private var cedulaDriver:String?=""
    private lateinit var textViewNameDriver: TextView
    private lateinit var textViewEmailDriver: TextView
    private lateinit var buttonSignOutDriver: Button
    private lateinit var buttonDeleteDriver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDriverBinding.inflate(inflater,container,false)
        //_binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textViewNameDriver= binding.textViewDriverName
        textViewEmailDriver= binding.textViewDriverEmail
        buttonSignOutDriver = binding.buttonContinuarDriver
        buttonDeleteDriver = binding.buttonEliminarDatosDriver

        parentFragmentManager.setFragmentResultListener("key",this, FragmentResultListener {
                Key, result ->
            cedulaDriver = result.getString("cedula")
            Log.d("CI", "La cédula: $cedulaDriver")
            UserService.getDriverData(cedulaDriver as String) { nameUser ->
                textViewNameDriver.text = nameUser.name + " " + nameUser.lastName
                textViewEmailDriver.text = nameUser.email
            }
        })

        buttonSignOutDriver.setOnClickListener { UserService.signOff() }
        buttonDeleteDriver.setOnClickListener {
            UserService.getUserId(cedulaDriver)
            val activity: Activity? = activity
            makeText(activity, "Datos eliminados exitosamente.", Toast.LENGTH_LONG).show()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}