package com.example.polimove.models

data class User(
    var id: String? = null,
    val cedula: String? = null,
    val contrasena: String? = null,
    val email: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    var routeId: String? = null
)
