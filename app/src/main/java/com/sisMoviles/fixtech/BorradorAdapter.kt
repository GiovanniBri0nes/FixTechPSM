package com.sisMoviles.fixtech

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BorradorAdapter (private val borradorList:List<BorradorModel>) : RecyclerView.Adapter<BorradorViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorradorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.borrador_item, parent, false)
        return BorradorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return borradorList.size
    }

    override fun onBindViewHolder(holder: BorradorViewHolder, position: Int) {
        holder.render(borradorList[position])
    }

}