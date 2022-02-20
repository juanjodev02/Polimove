package com.example.polimove.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val cedulaError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)