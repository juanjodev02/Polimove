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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.polimove.databinding.FragmentProfileBinding
import com.example.polimove.services.user.UserService

private const val USER_CI_PARAM = "1722951165"

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var cedula: String? = ""
    private lateinit var textViewNombreCompletoEst: TextView
    private lateinit var textViewCorreoEst: TextView
    private lateinit var buttonEliminar: Button
    private lateinit var buttonCerrarSesion: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        parentFragmentManager.setFragmentResultListener("key",this, FragmentResultListener{
            key, result ->
            cedula = result.getString("cedula")
            Log.d("CI", "La cedula es: $cedula")

        })*/
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        //cedula = arguments?.getString(USER_CI_PARAM)

        val root: View = binding.root
        textViewNombreCompletoEst = binding.textViewNombreCompletoEst
        textViewCorreoEst = binding.textViewCorreoEst
        buttonEliminar = binding.buttonEliminar
        buttonCerrarSesion = binding.buttonCerrarSesion

        parentFragmentManager.setFragmentResultListener("key",this, FragmentResultListener{
                key, result ->
            cedula = result.getString("cedula")
            Log.d("CI", "La cedula es: $cedula")
            UserService.getData(cedula as String){ nameUser ->
                textViewNombreCompletoEst.text = nameUser.name+" "+nameUser.lastName
                textViewCorreoEst.text = nameUser.email
            }

        })

        buttonCerrarSesion.setOnClickListener{
            UserService.signOff()
        val textViewNomCompletoEst: TextView = binding.textViewEstName
        profileViewModel.fullNameStd.observe(viewLifecycleOwner) {
            textViewNomCompletoEst.text = it
        }*/

        buttonEliminar.setOnClickListener{
            UserService.getUserId(cedula)
            val activity: Activity? = activity
            Toast.makeText(activity, "Datos eliminados exitosamente.", Toast.LENGTH_LONG)
        }


        val textViewCorreoEst: TextView = binding.textViewEstEmail
        profileViewModel.emailStd.observe(viewLifecycleOwner) {
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