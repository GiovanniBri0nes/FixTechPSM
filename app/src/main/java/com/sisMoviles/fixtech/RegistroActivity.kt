package com.sisMoviles.fixtech

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sisMoviles.fixtech.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val PICK_IMAGE_REQUEST = 1
    private var uriImagenSeleccionada: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Selección de imagen
        binding.btnRegistroImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Botón de registro
        binding.btnRegistroRegistro.setOnClickListener {
            val nombre = binding.etRegistroName.text.toString().trim()
            val apellido = binding.etRegistroApellidos.text.toString().trim()
            val email = binding.etRegistroEmail.text.toString().trim()
            val username = binding.etRegistroUsername.text.toString().trim()
            val password = binding.etRegistroPassword.text.toString().trim()
            val telefono = binding.etRegistroPhone.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || username.isEmpty()
                || password.isEmpty() || telefono.isEmpty() || uriImagenSeleccionada == null
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Aquí iría lógica para enviar datos al servidor o Firebase
            Toast.makeText(this, "Formulario válido, listo para enviar", Toast.LENGTH_SHORT).show()
        }
    }

    // Mostrar imagen seleccionada
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            uriImagenSeleccionada = data.data
            binding.imageView3.setImageURI(uriImagenSeleccionada)
        }
    }
}
