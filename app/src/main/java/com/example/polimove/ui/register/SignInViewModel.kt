package com.example.polimove.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.polimove.R
import com.example.polimove.ui.login.LoginFormState

class SignInViewModel {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm




    fun register(cedula:String,email: String, password: String){


    }

    fun signInViewModelDataChanged(cedula:String,email: String, password: String) {
        if (!isCedulaValid(cedula)) {
            //TODO
           // _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else if (!isEmailValid(email)) {
            //TODO
            //_loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isCedulaValid(username: String): Boolean {

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

    //Validación email Poli
    private fun isEmailValid(password: String): Boolean {
       //TODO
       // return password.length > 5
        return true;
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