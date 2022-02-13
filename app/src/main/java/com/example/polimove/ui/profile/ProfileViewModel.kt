package com.example.polimove.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _fullName = MutableLiveData<String>().apply {
        value = "JUAN JOSÉ JARAMILLO CAJAMARCA"
    }
    val fullNameStd: LiveData<String> = _fullName

    private val _email = MutableLiveData<String>().apply {
        value = "juanjo.jaramillo02@epn.edu.ec"
    }
    val emailStd: LiveData<String> = _email

    private val _number = MutableLiveData<String>().apply {
        value = "0998482373"
    }
    val numberStd: LiveData<String> = _number
}