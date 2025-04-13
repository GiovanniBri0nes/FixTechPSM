package com.sisMoviles.fixtech

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.databinding.BorradorItemBinding

class BorradorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding : BorradorItemBinding = BorradorItemBinding.bind(view)
    private var titulo = binding.borritemTxtTitulo
    private var descrpicion = binding.borritemTxtDescripcion

    fun render(borradorModel: BorradorModel){
            titulo.text = borradorModel.titulo
            descrpicion.text = borradorModel.descripcion
    }

}