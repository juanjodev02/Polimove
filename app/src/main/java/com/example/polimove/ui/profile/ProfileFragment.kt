package com.example.polimove.ui.profile

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.databinding.FragmentProfileBinding
import com.example.polimove.services.user.UserService

private const val USER_CI_PARAM = "1722951165"

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var textViewNombreCompletoEst: TextView
    private lateinit var textViewCorreoEst: TextView
    private lateinit var textViewCelularEst: TextView
    private lateinit var buttonEliminar: Button
    private lateinit var buttonCerrarSesion: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        //cedula = arguments?.getString(USER_CI_PARAM)
        cedula="1722951165"

        val root: View = binding.root
        textViewNombreCompletoEst = binding.textViewNombreCompletoEst
        textViewCorreoEst = binding.textViewCorreoEst
        textViewCelularEst = binding.textViewCelularEst
        buttonEliminar = binding.buttonEliminar
        buttonCerrarSesion = binding.buttonCerrarSesion

        UserService.getData(cedula as String){ nameUser ->
            textViewNombreCompletoEst.text = nameUser.name+" "+nameUser.lastName
            textViewCorreoEst.text = nameUser.email
            textViewCelularEst.text = nameUser.phone
        }


        buttonCerrarSesion.setOnClickListener{
            val activity: Activity? = activity
            activity?.finish()
        }

        buttonEliminar.setOnClickListener{
            UserService.getUserId(cedula)
            val activity: Activity? = activity
            Toast.makeText(activity, "Datos eliminados exitosamente.", Toast.LENGTH_LONG)
            activity?.finish()
        }






        /*
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewNomCompletoEst: TextView = binding.textViewNombreCompletoEst
        profileViewModel.nombreCompletoEst.observe(viewLifecycleOwner) {
            textViewNomCompletoEst.text = it
        }

        val textViewCorreoEst: TextView = binding.textViewCorreoEst
        profileViewModel.correoEst.observe(viewLifecycleOwner) {
            textViewCorreoEst.text = it
        }

        val textViewCelularEst: TextView = binding.textViewCelularEst
        profileViewModel.celularEst.observe(viewLifecycleOwner) {
            textViewCelularEst.text = it
        }
*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}