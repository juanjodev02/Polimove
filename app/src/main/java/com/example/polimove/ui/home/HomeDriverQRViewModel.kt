package com.example.polimove.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeDriverQRViewModel: ViewModel(){

        private val _QRCODE = MutableLiveData<String>().apply {
            value = "QRCODE"
        }
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