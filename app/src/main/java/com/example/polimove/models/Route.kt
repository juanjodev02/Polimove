package com.example.polimove.models

data class Route(
    var id: String? = null,
    val name: String? = null,
    val stops: ArrayList<String>? = null
)