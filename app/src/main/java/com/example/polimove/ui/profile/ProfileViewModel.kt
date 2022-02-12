package com.example.polimove.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {


    private val _nombreCompletoEst = MutableLiveData<String>().apply {
        value = "JUAN JOSÉ JARAMILLO CAJAMARCA"
    }
    val nombreCompletoEst: LiveData<String> = _nombreCompletoEst

    private val _correoEst = MutableLiveData<String>().apply {
        value = "juanjo.jaramillo02@epn.edu.ec"
    }
    val correoEst: LiveData<String> = _correoEst

    private val _celularEst = MutableLiveData<String>().apply {
        value = "0998482373"
    }
    val celularEst: LiveData<String> = _celularEst



}