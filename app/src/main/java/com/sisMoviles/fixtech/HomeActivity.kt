package com.sisMoviles.fixtech

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sisMoviles.fixtech.adapters.PublicacionAdapter
import com.sisMoviles.fixtech.api.RetrofitClient
import com.sisMoviles.fixtech.data.AppDatabase
import com.sisMoviles.fixtech.data.local.PublicacionEntity
import com.sisMoviles.fixtech.databinding.ActivityHomeBinding
import com.sisMoviles.fixtech.modelos.PublicacionModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: PublicacionAdapter
    private var publicacionesList: List<PublicacionModel> = emptyList()
    private lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "fixtech-db"
        ).build()


        binding.spinnerOrden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val orden = if (position == 0) "desc" else "asc"
                cargarPublicaciones(orden)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        binding.ibHomeBuscar.setOnClickListener {
            val texto = binding.etHomeBuscar.text.toString().trim()
            if (texto.isNotEmpty()) {
                buscarPublicaciones(texto)
            } else {
                cargarPublicaciones()
            }
        }


        binding.rcHomepublicaciones.layoutManager = LinearLayoutManager(this)

        binding.ibHomeUser.setOnClickListener {
            val intent = Intent(this, PerfilUsuarioActivity::class.java)
            startActivity(intent)
        }

        binding.ibHomeAgregarPublicacion.setOnClickListener {
            val intent = Intent(this, CrearPublicacionActivity::class.java)
            startActivity(intent)
        }

        binding.ibHomeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        cargarPublicaciones()
    }

    private fun cargarPublicaciones(orden: String = "desc") {
        RetrofitClient.instance.obtenerPublicacionesOrdenadas(orden)
            .enqueue(object : Callback<List<PublicacionModel>> {
                override fun onResponse(
                    call: Call<List<PublicacionModel>>,
                    response: Response<List<PublicacionModel>>
                ) {
                    if (response.isSuccessful) {
                        val publicaciones = response.body() ?: emptyList()

                        publicacionesList = publicaciones
                        adapter = PublicacionAdapter(
                            publicaciones,
                            onItemClick = { publicacion ->
                                val intent = Intent(this@HomeActivity, PerfilUsuarioActivity::class.java)
                                intent.putExtra("id_perfil", publicacion.id_usuario)
                                startActivity(intent)
                            },
                            onDeleteClick = { publicacion ->
                                mostrarDialogoEliminar(publicacion.id) { cargarPublicaciones(orden) }
                            }
                        )
                        binding.rcHomepublicaciones.adapter = adapter

                        // Guardar en Room
                        lifecycleScope.launch {
                            database.publicacionDao().eliminarTodas()
                            database.publicacionDao().insertarTodas(mapToEntityList(publicaciones))
                        }

                    } else {
                        Toast.makeText(this@HomeActivity, "Error al cargar publicaciones", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<PublicacionModel>>, t: Throwable) {
                    // Sin conexión, cargar de Room
                    Toast.makeText(this@HomeActivity, "Sin conexión. Cargando datos locales", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        val publicacionesLocales = database.publicacionDao().obtenerTodas()
                        runOnUiThread {
                            adapter = PublicacionAdapter(
                                publicacionesLocales.map {
                                    PublicacionModel(
                                        id = it.id,
                                        titulo = it.titulo,
                                        descripcion = it.descripcion,
                                        imagen = it.imagen,
                                        fecha_creacion = it.fecha_creacion,
                                        autor = it.autor,
                                        foto_perfil = it.foto_perfil,
                                        id_usuario = it.id_usuario
                                    )
                                },
                                onItemClick = { publicacion ->
                                    val intent = Intent(this@HomeActivity, PerfilUsuarioActivity::class.java)
                                    intent.putExtra("id_perfil", publicacion.id_usuario)
                                    startActivity(intent)
                                },
                                onDeleteClick = null // no permitimos eliminar sin conexión
                            )
                            binding.rcHomepublicaciones.adapter = adapter
                        }
                    }.start()
                }
            })
    }

    private fun mostrarDialogoEliminar(idPublicacion: Int, onSuccess: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("¿Eliminar publicación?")
            .setMessage("Esta acción no se puede deshacer.")
            .setPositiveButton("Eliminar") { _, _ ->
                RetrofitClient.instance.eliminarPublicacion(idPublicacion)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@HomeActivity, "Publicación eliminada", Toast.LENGTH_SHORT).show()
                                onSuccess()
                            } else {
                                Toast.makeText(this@HomeActivity, "Error al eliminar", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(this@HomeActivity, "Fallo de red", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun buscarPublicaciones(query: String) {
        RetrofitClient.instance.buscarPublicaciones(query)
            .enqueue(object : Callback<List<PublicacionModel>> {
                override fun onResponse(
                    call: Call<List<PublicacionModel>>,
                    response: Response<List<PublicacionModel>>
                ) {
                    if (response.isSuccessful) {
                        val resultados = response.body() ?: emptyList()
                        adapter = PublicacionAdapter(
                            resultados, // ← aquí estaba el error
                            onItemClick = { publicacion ->
                                val intent = Intent(this@HomeActivity, PerfilUsuarioActivity::class.java)
                                intent.putExtra("id_perfil", publicacion.id_usuario)
                                startActivity(intent)
                            },
                            onDeleteClick = { publicacion ->
                                mostrarDialogoEliminar(publicacion.id) {
                                    buscarPublicaciones(query)
                                }
                            }
                        )
                        binding.rcHomepublicaciones.adapter = adapter
                    } else {
                        Toast.makeText(this@HomeActivity, "Error al buscar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<PublicacionModel>>, t: Throwable) {
                    Toast.makeText(this@HomeActivity, "Fallo de red", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun mapToEntityList(lista: List<PublicacionModel>): List<PublicacionEntity> {
        return lista.map {
            PublicacionEntity(
                id = it.id,
                titulo = it.titulo,
                descripcion = it.descripcion,
                imagen = it.imagen,
                fecha_creacion = it.fecha_creacion,
                autor = it.autor,
                foto_perfil = it.foto_perfil,
                id_usuario = it.id_usuario
            )
        }
    }

    override fun onResume() {
        super.onResume()
        cargarPublicaciones()
    }
}
