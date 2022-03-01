package com.example.polimove.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    private val _QRCODE = MutableLiveData<String>().apply {
        value = "QRCODE"
    }
    val text: LiveData<String> = _text

    private val _emoji = MutableLiveData<String>().apply {
        value = String(Character.toChars(0x1F44B))
    }
    val emoji: LiveData<String> = _emoji

     val _nombreRuta = MutableLiveData<String>().apply {
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


    val QRCODEDRIVER: LiveData<String> = _QRCODE
    private val _nameStd = MutableLiveData<String>().apply {
        value = "LESLY TIPANLUIZA"
    }
    val nameStd: LiveData<String> = _nameStd
    private val _Asiento = MutableLiveData<String>().apply {
        value = "12-B"
    }
    val numberAsiento: LiveData<String> = _Asiento
}