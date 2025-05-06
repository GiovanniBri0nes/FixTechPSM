package com.sisMoviles.fixtech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.adapters.BorradorAdapter
import com.sisMoviles.fixtech.adapters.HomeAdapter
import com.sisMoviles.fixtech.databinding.ActivityBorradoresBinding
import com.sisMoviles.fixtech.databinding.ActivityHomeBinding
import com.sisMoviles.fixtech.modelos.BorradorModel
import com.sisMoviles.fixtech.modelos.PublicacionModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerViewHome : RecyclerView
    private lateinit var adapter: HomeAdapter
    private lateinit var publicacionList: List<PublicacionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBorradoresPrueba()

        recyclerViewHome = binding.rcHomepublicaciones
        recyclerViewHome.layoutManager = LinearLayoutManager(this)
        adapter = HomeAdapter(publicacionList)
        recyclerViewHome.adapter = adapter
    }


    private fun initBorradoresPrueba()
    {
        publicacionList = listOf(
            PublicacionModel("titulo 1", "descripcion 1", "fecha 1", "autor 1"),
            PublicacionModel("titulo 2", "descripcion 1", "fecha 1", "autor 1"),
            PublicacionModel("titulo 3", "descripcion 1", "fecha 1", "autor 1"))
    }
}