package com.example.polimove.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeConductorBinding
import com.example.polimove.services.users.UserService
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME

private const val USER_CI_PARAM = "1751438498"
class HomeConductorFragment : Fragment() {
    private lateinit var buttonAgregarPasajaero: Button

    private var _binding: FragmentHomeConductorBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var nameTextView: TextView
    private lateinit var textViewRuta: TextView
    private lateinit var buttonAgregarPasajero: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeConductorBinding.inflate(inflater, container, false)
        val root: View = binding.root
        nameTextView = binding.textHome
        textViewRuta = binding.textViewNombreRuta
        buttonAgregarPasajero = binding.buttonAgregarPasajero

        val listadoLeido = ReadInformation()
        if(listadoLeido?.first != null){
            this.cedula = listadoLeido.first
            Log.d("cedula", this.cedula.toString())
            UserService.getDriverData(this.cedula.toString()) { nameUser ->
                nameTextView.text = "¡Hola! "+String(Character.toChars(0x1F44B))+" "+nameUser.name +" "+nameUser.lastName

                UserService.getRouteName(nameUser.routeId){routename ->
                    textViewRuta.text = "Tu ruta es: "+routename
                }

            }
        }



        buttonAgregarPasajero.setOnClickListener{
            //ir a pantalla escanear QR
        }



        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAgregarPasajaero.setOnClickListener{
            findNavController().navigate(R.id.action_home_conductor_to_home_driverqr)
        }
    }

    fun ReadInformation():Pair<String,String>{
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