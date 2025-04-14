package com.sisMoviles.fixtech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.adapters.BorradorAdapter
import com.sisMoviles.fixtech.adapters.PublicacionAdapter
import com.sisMoviles.fixtech.databinding.ActivityBorradoresBinding
import com.sisMoviles.fixtech.databinding.ActivityPerfilusuarioBinding

class PerfilUsuarioActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPerfilusuarioBinding
    private lateinit var adapter: PublicacionAdapter
    private lateinit var recyclerPublicacion: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfilusuario)
        binding = ActivityPerfilusuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun initPublicaciones()
    {
        recyclerPublicacion = binding.rcPublicacionperfil
        recyclerPublicacion.layoutManager = LinearLayoutManager(this)
        

    }
}