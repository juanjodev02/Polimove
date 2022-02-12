package com.example.polimove.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeNoRouteViewModel  : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "¡Hola "+"JUAN JARAMILLO!"
    }
    val text: LiveData<String> = _text

}