package com.sisMoviles.fixtech

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sisMoviles.fixtech.databinding.ActivityLoginBinding  // Asegúrate de que coincida con tu XML

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var botonInicioSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginActivity = this  // Vincula esta Activity al XML
        botonInicioSesion = binding.loginBtnIniciosesion;
        navigateBorradores();
    }

    fun navigateToRegister() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    private fun navigateBorradores() {
        botonInicioSesion.setOnClickListener {
            val intent = Intent(this, BorradorActivity::class.java)
            startActivity(intent)
        }
    }
}