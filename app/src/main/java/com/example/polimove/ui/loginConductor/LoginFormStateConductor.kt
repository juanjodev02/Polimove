package com.example.polimove.ui.loginConductor

/**
 * Data validation state of the login form.
 */
data class LoginFormStateConductor(
    val cedulaError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)