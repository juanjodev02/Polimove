package com.example.polimove.ui.register

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.example.polimove.MainActivity
import com.example.polimove.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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

            registro(cedula,email,password)
        }
    }

    fun registro(cedula:String,email:String,password: String){
        val db = Firebase.firestore


        db.collection("estudiantes")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    if(document.data["cedula"]?.equals(cedula)==true){
                        val user = hashMapOf(
                            "cedula" to cedula,
                            "contrasena" to password,
                            "email" to email,
                            "name" to document.data["name"].toString(),
                            "lastName" to document.data["lastName"].toString()
                        )
                        db.collection("usuarios")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                                    if(it.isSuccessful){
                                        Log.d(ContentValues.TAG, "Registro copletado exitosamente")
                                        val intent = Intent(activity, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                    else{
                                        Log.d(ContentValues.TAG, "No se pudo crear el usuario")
                                    }

                                }
                            }
                            .addOnFailureListener { e ->
                                Log.w(ContentValues.TAG, "Error adding document", e)
                            }
                    }
                }
            }.addOnFailureListener { exception ->
                Log.w("TAG", "Hay un error con su registro", exception)
            }





    }

}