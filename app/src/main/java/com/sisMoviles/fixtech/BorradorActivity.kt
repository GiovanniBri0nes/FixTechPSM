package com.sisMoviles.fixtech

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sisMoviles.fixtech.adapters.BorradorAdapter
import com.sisMoviles.fixtech.api.RetrofitClient
import com.sisMoviles.fixtech.databinding.ActivityBorradoresBinding
import com.sisMoviles.fixtech.modelos.BorradorModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BorradorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorradoresBinding
    private lateinit var adapter: BorradorAdapter
    private var borradoresList: MutableList<BorradorModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorradoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBorradorHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.ibBorradorUser.setOnClickListener {
            startActivity(Intent(this, PerfilUsuarioActivity::class.java))
        }

        val idUsuario = getSharedPreferences("fixtech_prefs", MODE_PRIVATE).getInt("id", -1)
        if (idUsuario != -1) cargarBorradores(idUsuario)
        else Toast.makeText(this, "ID de usuario no disponible", Toast.LENGTH_SHORT).show()
    }

    private fun cargarBorradores(idUsuario: Int) {
        RetrofitClient.instance.obtenerBorradoresPorUsuario(idUsuario)
            .enqueue(object : Callback<List<BorradorModel>> {
                override fun onResponse(
                    call: Call<List<BorradorModel>>,
                    response: Response<List<BorradorModel>>
                ) {
                    if (response.isSuccessful) {
                        borradoresList = response.body()?.toMutableList() ?: mutableListOf()

                        adapter = BorradorAdapter(
                            borradoresList,
                            onEditarClick = { borrador ->
                                val intent = Intent(this@BorradorActivity, CrearPublicacionActivity::class.java)
                                intent.putExtra("id", borrador.id)
                                intent.putExtra("titulo", borrador.titulo)
                                intent.putExtra("descripcion", borrador.descripcion)
                                intent.putExtra("imagen", borrador.imagen)
                                startActivity(intent)
                            },
                            onDeleteClick = { borrador ->
                                androidx.appcompat.app.AlertDialog.Builder(this@BorradorActivity)
                                    .setTitle("¿Eliminar borrador?")
                                    .setMessage("Esta acción no se puede deshacer.")
                                    .setPositiveButton("Eliminar") { _, _ ->
                                        RetrofitClient.instance.eliminarPublicacion(borrador.id)
                                            .enqueue(object : Callback<ResponseBody> {
                                                override fun onResponse(
                                                    call: Call<ResponseBody>,
                                                    response: Response<ResponseBody>
                                                ) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(this@BorradorActivity, "Borrador eliminado", Toast.LENGTH_SHORT).show()
                                                        borradoresList.remove(borrador)
                                                        adapter.notifyDataSetChanged()
                                                    } else {
                                                        Toast.makeText(this@BorradorActivity, "Error al eliminar", Toast.LENGTH_SHORT).show()
                                                    }
                                                }

                                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                                    Toast.makeText(this@BorradorActivity, "Fallo de red", Toast.LENGTH_SHORT).show()
                                                }
                                            })
                                    }
                                    .setNegativeButton("Cancelar", null)
                                    .show()
                            }
                        )

                        binding.rcBorradores.layoutManager = LinearLayoutManager(this@BorradorActivity)
                        binding.rcBorradores.adapter = adapter
                    } else {
                        Log.e("BorradorAPI", "Error ${response.code()}: ${response.errorBody()?.string()}")
                        Toast.makeText(this@BorradorActivity, "Error al cargar borradores", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<BorradorModel>>, t: Throwable) {
                    Log.e("BorradorAPI", "Fallo de red: ${t.message}", t)
                    Toast.makeText(this@BorradorActivity, "Fallo de red", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        val idUsuario = getSharedPreferences("fixtech_prefs", MODE_PRIVATE).getInt("id", -1)
        cargarBorradores(idUsuario)
    }
}
