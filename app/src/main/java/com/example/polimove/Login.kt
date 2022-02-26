package com.example.polimove




import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.polimove.ui.login.LoginFragment
import com.example.polimove.ui.register.SignInFragment


class Login : AppCompatActivity() {
    private lateinit var buttonSignIn: Button
    var loginView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Polimove)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        }


}