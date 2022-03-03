package com.example.polimove.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.polimove.Login
import com.example.polimove.databinding.FragmentProfileBinding
import com.example.polimove.services.users.UserService
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var textViewNombreCompletoEst: TextView
    private lateinit var textViewCorreoEst: TextView
    private lateinit var buttonEliminar: Button
    private lateinit var buttonCerrarSesion: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val root: View = binding.root
        textViewNombreCompletoEst = binding.textViewNombreCompletoEst
        textViewCorreoEst = binding.textViewCorreoEst
        buttonEliminar = binding.buttonEliminar
        buttonCerrarSesion = binding.buttonCerrarSesion


        val listadoLeido = this.readInformation()
        if(listadoLeido?.first != null) {
            this.cedula = listadoLeido.first
            UserService.getDriverData(this.cedula as String) { nameUser ->
                textViewNombreCompletoEst.text = nameUser.name + " " + nameUser.lastName
                textViewCorreoEst.text = nameUser.email
            }

            buttonCerrarSesion.setOnClickListener {
                UserService.signOff()
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)

            }

            buttonEliminar.setOnClickListener {
                UserService.getDriverId(this.cedula.toString())
                val activity: Activity? = activity
                Toast.makeText(activity, "Datos eliminados exitosamente.", Toast.LENGTH_LONG)
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)
            }


        }

        return root
    }

    fun readInformation():Pair<String,String>{
        val sharedPref = context?.getSharedPreferences(SHAREDINFO_FILENAME.toString(),0)
        val cedula = sharedPref?.getString(LOGIN_KEY,"").toString()
        val clave = sharedPref?.getString(PASSWORD_KEY,"").toString()
        return (cedula to clave)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}