package com.example.polimove.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polimove.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginViewModel() : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

      fun login(cedula: String, password: String) {
          inicioSesionConCedula(cedula,password)
    }

    //Función para autenticarse con email y contraseña
    fun auth(email:String,password: String){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                Log.d("TAG","$email contraseña $password")
                if(it.isSuccessful){
                    Log.d("TAG","Se ha autenticado")
                }
                else{
                    Log.d("TAG","No se ha accedido")
                }
            }
    }

   fun inicioSesionConCedula(cedula:String,password: String){
        var encontrado  = ""
        val db = Firebase.firestore
        db.collection("usuarios")
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
                Log.w("TAG", "Hay un error con sus credenciales", exception)
            }

    }


    //Debido a que nuestro login es con cedula, se tiene que obtener los datos
    //del usuario, si existe, buscara su correo para poder authenticarse
    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(cedulaError = R.string.invalid_cedula)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {

        var bandera = false

        if (!(username.equals(""))) {
            bandera = validacionCedula(username)
        }

        return bandera;
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    //Validación para cédula ecuatoriana
    private fun validacionCedula(document: String): Boolean {
        var sum: Byte = 0
        try {
            if (document.trim { it <= ' ' }.length != 10){
                return false
            }
            var data: MutableList<String>
            data = document.split("").toMutableList()
            data.removeAt(0)
            data.removeAt(10)
            var verifier = (data[0] + data[1]).toByte()
            if (verifier < 1 || verifier > 24) {
                return false
            }
            val digits = ByteArray(data.size)
            for (i in digits.indices) digits[i] = data[i].toByte()
            if (digits[2] > 6){
                return false
            }

            for (i in 0 until digits.size - 1) {
                var cor = digits[i]
                if (i % 2 == 0) {
                    verifier = (digits[i] * 2).toByte()
                    if (verifier > 9) verifier = (verifier - 9).toByte()
                } else verifier = (digits[i] * 1).toByte()
                sum = (sum + verifier).toByte()
            }
            var sobrante = (sum%10)
            if ((10-sobrante).toByte()==digits[9]){
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }





}