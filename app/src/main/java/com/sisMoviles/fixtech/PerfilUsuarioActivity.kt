package com.sisMoviles.fixtech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.adapters.PublicacionAdapter
import com.sisMoviles.fixtech.databinding.ActivityPerfilusuarioBinding
import com.sisMoviles.fixtech.modelos.PublicacionModel

class PerfilUsuarioActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPerfilusuarioBinding
    private lateinit var adapter: PublicacionAdapter
    private lateinit var recyclerPublicacion: RecyclerView
    private lateinit var publicacionesList: List<PublicacionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfilusuario)
        binding = ActivityPerfilusuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPublicacionesPrueba()
        initPublicaciones()

    }

    fun initPublicaciones()
    {
        recyclerPublicacion = binding.rcPublicacionperfil
        recyclerPublicacion.layoutManager = LinearLayoutManager(this)
        adapter = PublicacionAdapter(publicacionesList)
        recyclerPublicacion.adapter = adapter
    }

    private fun initPublicacionesPrueba()
    {
        publicacionesList = listOf(
            PublicacionModel("titulo 1", "descripcion 1", "fecha 1", "autor 1"),
            PublicacionModel("titulo 2", "descripcion 1", "fecha 1", "autor 1"),
            PublicacionModel("titulo 3", "descripcion 1", "fecha 1", "autor 1")

        )
    }
}