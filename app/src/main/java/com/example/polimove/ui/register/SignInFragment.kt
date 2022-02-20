package com.example.polimove.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.example.polimove.R
import com.example.polimove.databinding.FragmentLoginBinding
import com.example.polimove.databinding.FragmentSignInBinding
import com.example.polimove.ui.login.LoginViewModel
import com.example.polimove.ui.login.LoginViewModelFactory


class SignInFragment : Fragment() {

    private lateinit var signInViewModel: SignInViewModel
    private var _binding: FragmentSignInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextNumberCedula = binding.editTextNumberCedula2
        val editTextTextPassword2 = binding.editTextTextPassword2
        val editTextTextCorreoInstitucional = binding.editTextTextCorreoInstitucional
        val buttonRegistrarse = binding.buttonRegistrarse
        val editTextTextRepetirContrasena = binding.editTextTextRepetirContrasena



        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                signInViewModel.signInViewModelDataChanged(
                    editTextNumberCedula.text.toString(),
                    editTextTextCorreoInstitucional.text.toString(),
                    editTextTextPassword2.text.toString()
                )
            }
        }
        editTextNumberCedula.addTextChangedListener(afterTextChangedListener)
        editTextTextCorreoInstitucional.addTextChangedListener(afterTextChangedListener)
        editTextTextPassword2.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInViewModel.register(
                    editTextNumberCedula.text.toString(),
                    editTextTextCorreoInstitucional.text.toString(),
                    editTextTextPassword2.text.toString()
                )
            }
            false
        }



        buttonRegistrarse.setOnClickListener{

        }
    }



}