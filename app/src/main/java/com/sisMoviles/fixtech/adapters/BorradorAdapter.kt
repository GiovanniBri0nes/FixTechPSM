package com.sisMoviles.fixtech.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisMoviles.fixtech.R
import com.sisMoviles.fixtech.modelos.BorradorModel
import com.sisMoviles.fixtech.viewHolders.BorradorViewHolder

class BorradorAdapter(
    private val borradorList: List<BorradorModel>,
    private val onEditarClick: (BorradorModel) -> Unit,
    private val onDeleteClick: (BorradorModel) -> Unit
) : RecyclerView.Adapter<BorradorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorradorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.borrador_item, parent, false)
        return BorradorViewHolder(view, onEditarClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: BorradorViewHolder, position: Int) {
        holder.render(borradorList[position])
    }

    override fun getItemCount(): Int = borradorList.size
}
