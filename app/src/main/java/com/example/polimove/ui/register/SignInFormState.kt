package com.example.polimove.ui.register

data class SignInFormState(
    val cedulaError: Int? = null,
    val emailError:Int?=null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)