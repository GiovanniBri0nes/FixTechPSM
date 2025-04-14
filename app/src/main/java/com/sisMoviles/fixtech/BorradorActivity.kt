package com.sisMoviles.fixtech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.adapters.BorradorAdapter
import com.sisMoviles.fixtech.databinding.ActivityBorradoresBinding
import com.sisMoviles.fixtech.modelos.BorradorModel


class BorradorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorradoresBinding
    private lateinit var recyclerViewBorradores : RecyclerView
    private lateinit var adapter: BorradorAdapter
    private lateinit var borradoresList: List<BorradorModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borradores)
        binding = ActivityBorradoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBorradoresPrueba()

        recyclerViewBorradores = binding.rcBorradores
        recyclerViewBorradores.layoutManager = LinearLayoutManager(this)
        adapter = BorradorAdapter(borradoresList)
        recyclerViewBorradores.adapter = adapter
    }


    private fun initBorradoresPrueba()
    {
        borradoresList = listOf(
            BorradorModel("Titulo 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur euismod vulputate erat, a aliquet neque bibendum eget. Donec lobortis tortor nec dui imperdiet fermentum. Pellentesque mollis, elit id cursus rhoncus, eros massa bibendum nisl, nec tristique ante libero sed leo. Sed consectetur tellus at odio tristique, id faucibus odio rhoncus. Quisque sapien est, hendrerit in porta eu, tristique ut ligula. Nullam vitae semper mi. Curabitur molestie ex magna, vitae pharetra libero venenatis id. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris auctor orci erat, ut scelerisque justo eleifend vitae."),
            BorradorModel("Titulo 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur euismod vulputate erat, a aliquet neque bibendum eget. Donec lobortis tortor nec dui imperdiet fermentum. Pellentesque mollis, elit id cursus rhoncus, eros massa bibendum nisl, nec tristique ante libero sed leo. Sed consectetur tellus at odio tristique, id faucibus odio rhoncus. Quisque sapien est, hendrerit in porta eu, tristique ut ligula. Nullam vitae semper mi. Curabitur molestie ex magna, vitae pharetra libero venenatis id. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris auctor orci erat, ut scelerisque justo eleifend vitae. 2"),
            BorradorModel("Titulo 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur euismod vulputate erat, a aliquet neque bibendum eget. Donec lobortis tortor nec dui imperdiet fermentum. Pellentesque mollis, elit id cursus rhoncus, eros massa bibendum nisl, nec tristique ante libero sed leo. Sed consectetur tellus at odio tristique, id faucibus odio rhoncus. Quisque sapien est, hendrerit in porta eu, tristique ut ligula. Nullam vitae semper mi. Curabitur molestie ex magna, vitae pharetra libero venenatis id. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris auctor orci erat, ut scelerisque justo eleifend vitae. 3"),
        )
    }
}