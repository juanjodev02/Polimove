package com.example.polimove.models

data class User(
    val cedula: String? = null,
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var routeId: String? = null,
    var phone: String? = null
)
