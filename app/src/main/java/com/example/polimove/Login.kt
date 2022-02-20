package com.example.polimove




import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Polimove)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}