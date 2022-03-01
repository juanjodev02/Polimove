package com.example.polimove.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.R
import com.example.polimove.databinding.FragmentHomeBinding
import com.example.polimove.databinding.FragmentHomeConductorBinding

private const val USER_CI_PARAM = "1751438498"
class HomeConductorFragment : Fragment() {

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
        //cedula = arguments?.getString(USER_CI_PARAM)
        cedula="1751438498"
        val root: View = binding.root
        nameTextView = binding.textHome
        textViewRuta = binding.textViewNombreRuta
        buttonAgregarPasajero = binding.buttonAgregarPasajero


        UserService.getData(cedula as String) { nameUser ->
            nameTextView.text = "¡Hola! "+String(Character.toChars(0x1F44B))+" "+nameUser.name +" "+nameUser.lastName

            UserService.getRouteName(nameUser.routeId){routename ->
                textViewTitulo.text = "Tu ruta es: "+routename
            }

        }

        buttonAgregarPasajero.setOnClickListener{
            //ir a pantalla escanear QR
        }



        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}