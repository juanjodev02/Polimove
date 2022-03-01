package com.example.polimove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginConductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Polimove)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_conductor)
    }
}