package com.sisMoviles.fixtech

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sisMoviles.fixtech.databinding.ActivityLoginBinding  // Asegúrate de que coincida con tu XML

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginActivity = this  // Vincula esta Activity al XML
    }


    fun navigateToRegister() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)


    }
}