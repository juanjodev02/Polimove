package com.example.polimove.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "¡Hola "+"JUAN JARAMILLO!"
    }
    val text: LiveData<String> = _text

    private val _emoji = MutableLiveData<String>().apply {
        value = String(Character.toChars(0x1F44B))
    }
    val emoji: LiveData<String> = _emoji

    private val _nombreRuta = MutableLiveData<String>().apply {
        value = "Tu ruta: "+"PUEMBO"
    }
    val nombreRuta: LiveData<String> = _nombreRuta

    private val _codigo = MutableLiveData<String>().apply {
        value = "asdfghjklñ"
    }
    val codigo: LiveData<String> = _codigo

    private val _QRCODE = MutableLiveData<String>().apply {
        value = "QRCODE"
    }
    val QRCODE: LiveData<String> = _QRCODE


}