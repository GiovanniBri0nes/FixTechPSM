package com.sisMoviles.fixtech.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.databinding.BorradorItemBinding
import com.sisMoviles.fixtech.databinding.PublicacionItemBinding
import com.sisMoviles.fixtech.modelos.BorradorModel
import com.sisMoviles.fixtech.modelos.PublicacionModel

class PublicacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding : PublicacionItemBinding = PublicacionItemBinding.bind(view)
    private var titulo = binding.piTxtTitulo
    private var descripcion = binding.piTxtDescripcion
    private var imagen = binding.piImgImagen
    private var fecha = binding.piTxtFecha
    private var imagenUsuario = binding.piImgUsuario
    private var usuario = binding.piTxtUsuario

    fun render(publicacionModel: PublicacionModel){
        titulo.text = publicacionModel.titulo
        descripcion.text = publicacionModel.descripcion
        fecha.text = publicacionModel.fecha
        usuario.text = publicacionModel.autor

    }

}