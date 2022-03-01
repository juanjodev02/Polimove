package com.example.polimove.ui.loginConductor


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.polimove.MainActivity
import com.example.polimove.databinding.FragmentLoginConductorBinding
import com.example.polimove.sharedPreferences.LOGIN_KEY
import com.example.polimove.sharedPreferences.PASSWORD_KEY
import com.example.polimove.sharedPreferences.SHAREDINFO_FILENAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginFragmentConductor : Fragment() {

    private lateinit var loginViewModelConductor: LoginViewModelConductor
    private var _binding: FragmentLoginConductorBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentLoginConductorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModelConductor = LoginViewModelConductor()

        val editTextNumberCedula = binding.editTextNumberCedula
        val editTextTextPassword = binding.editTextTextPassword
        val buttonIniciarSesion = binding.buttonIniciarSesion
        val checkBoxRecordarme = binding.checkBoxGuardarInformacion


        //LEEMOS LOS DATOS DESDE EL SHARED PREFERENCES
        LeerDatosDePreferencias(editTextNumberCedula,editTextTextPassword,checkBoxRecordarme)

        loginViewModelConductor.loginFormStateConductor.observe(viewLifecycleOwner,
            Observer { loginFormStateConductor ->
                if (loginFormStateConductor == null) {
                    return@Observer
                }
                buttonIniciarSesion.isEnabled = loginFormStateConductor.isDataValid
                loginFormStateConductor.cedulaError?.let {
                    editTextNumberCedula.error = getString(it)
                }
                loginFormStateConductor.passwordError?.let {
                    editTextTextPassword.error = getString(it)
                }
            })


        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModelConductor.loginDataChanged(
                    editTextNumberCedula.text.toString(),
                    editTextTextPassword.text.toString()
                )
            }
        }
        editTextNumberCedula.addTextChangedListener(afterTextChangedListener)
        editTextTextPassword.addTextChangedListener(afterTextChangedListener)
        editTextTextPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login(
                    editTextNumberCedula.text.toString(),
                    editTextTextPassword.text.toString()
                )
            }
            false
        }

        buttonIniciarSesion.setOnClickListener {
            GuardarDatosEnPreferencias(editTextNumberCedula, editTextTextPassword,checkBoxRecordarme)
            login(
                editTextNumberCedula.text.toString(),
                editTextTextPassword.text.toString()
            )

        }


    }

    fun LeerDatosDePreferencias(editTextNumberCedula:EditText,editTextTextPassword:EditText,checkBoxRecordarme: CheckBox){
        val listadoLeido = ReadInformation()
        if(listadoLeido?.first != null){
            checkBoxRecordarme.isChecked = true
        }
        editTextNumberCedula.setText ( listadoLeido?.first)
        editTextTextPassword.setText ( listadoLeido?.second )
    }
    fun GuardarDatosEnPreferencias(editTextNumberCedula:EditText,editTextTextPassword:EditText,checkBoxRecordarme: CheckBox){

        val email = editTextNumberCedula.text.toString()
        val clave = editTextTextPassword.text.toString()
        val listadoAGrabar:Pair<String,String>
        if(checkBoxRecordarme.isChecked){
            listadoAGrabar = email to clave
        }
        else{
            listadoAGrabar ="" to ""
        }
        SaveInformation(listadoAGrabar)
    }


    fun SaveInformation(datosAGrabar:Pair<String,String>){
        val sharedPref = context?.getSharedPreferences(SHAREDINFO_FILENAME.toString(),0)
        val editor = sharedPref?.edit()
        editor?.putString(LOGIN_KEY , datosAGrabar.first)
        editor?.putString(PASSWORD_KEY, datosAGrabar.second)
        editor?.apply()
    }
    fun ReadInformation():Pair<String,String>{
        val sharedPref = context?.getSharedPreferences(SHAREDINFO_FILENAME.toString(),0)
        val cedula = sharedPref?.getString(LOGIN_KEY,"").toString()
        val clave = sharedPref?.getString(PASSWORD_KEY,"").toString()
        return (cedula to clave)
    }

    fun login(cedula: String, password: String){
        inicioSesionConCedula(cedula,password)
    }

    fun inicioSesionConCedula(cedula:String,password: String){
        var encontrado  = ""

        val db = Firebase.firestore
        db.collection("conductores")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.data["cedula"]?.equals(cedula) == true){
                        encontrado=document.data["email"].toString()
                        auth(encontrado,password)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Hay un error con sus credenciales")
            }


    }

    //Función para autenticarse con email y contraseña
    fun auth(email:String,password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Log.d("TAG","No se ha accedido")
                Toast.makeText(context, "Sus credenciales no son correctas", Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}