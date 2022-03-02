package com.example.polimove.ui.register

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polimove.R
import com.example.polimove.ui.login.LoginFormState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignInViewModel(): ViewModel() {
    private val _signInForm = MutableLiveData<SignInFormState>()
    val signInViewModel: LiveData<SignInFormState> = _signInForm


    fun register(cedula: String, email: String, password: String, toString: String){


    }

    fun signInViewModelDataChanged(cedula:String,email: String, password: String,password2: String) {
        if (!isCedulaValid(cedula)) {
            _signInForm.value = SignInFormState(cedulaError = R.string.invalid_cedula)
        }else if(!isEmailValid(email)){
            _signInForm.value = SignInFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _signInForm.value = SignInFormState(passwordError = R.string.invalid_password)
        } else if (!areEqualsPass(password,password2)) {
            _signInForm.value = SignInFormState(isDataValid = true)
        }
    }

    private fun areEqualsPass(pass1:String,pass2:String):Boolean{
        if(pass1.equals(pass2))
            return true
        return false
    }

    // A placeholder username validation check
    private fun isCedulaValid(username: String): Boolean {

        var bandera = false

        if (!(username.equals(""))) {
            bandera = validacionCedula(username)
        }

        return bandera
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8
    }

    //Validación email Poli
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
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