package com.example.polimove.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.example.polimove.databinding.FragmentSignInBinding


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

        signInViewModel = SignInViewModel()
        val editTextNumberCedula = binding.editTextNumberCedula2
        val editTextTextPassword2 = binding.editTextTextPassword2
        val editTextTextCorreoInstitucional = binding.editTextTextCorreoInstitucional
        val buttonRegistrarse = binding.buttonRegistrarse
        val editTextTextRepetirContrasena = binding.editTextTextRepetirContrasena

        signInViewModel.signInViewModel.observe(viewLifecycleOwner,
            Observer { signInFormState ->
                if (signInFormState == null) {
                    return@Observer
                }
                buttonRegistrarse.isEnabled = signInFormState.isDataValid
                signInFormState.cedulaError?.let {
                    editTextNumberCedula.error = getString(it)
                }
                signInFormState.emailError?.let {
                    editTextTextCorreoInstitucional.error = getString(it)
                }
                signInFormState.passwordError?.let {
                    editTextTextPassword2.error = getString(it)
                }
            })

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
                    editTextTextPassword2.text.toString(),
                    editTextTextRepetirContrasena.text.toString()
                )
            }
        }
        editTextNumberCedula.addTextChangedListener(afterTextChangedListener)
        editTextTextCorreoInstitucional.addTextChangedListener(afterTextChangedListener)
        editTextTextPassword2.addTextChangedListener(afterTextChangedListener)
        editTextTextRepetirContrasena.addTextChangedListener(afterTextChangedListener)
        editTextTextRepetirContrasena.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInViewModel.register(
                    editTextNumberCedula.text.toString(),
                    editTextTextCorreoInstitucional.text.toString(),
                    editTextTextPassword2.text.toString(),
                    editTextTextRepetirContrasena.text.toString()
                )
            }
            false
        }



        buttonRegistrarse.setOnClickListener{
            var cedula =  editTextNumberCedula.text.toString()
            var email =editTextTextCorreoInstitucional.text.toString()
            var password =editTextTextPassword2.text.toString()

            signInViewModel.registro(cedula,email,password)
        }
    }



}