package com.sisMoviles.fixtech.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicaciones")
data class PublicacionEntity(
    @PrimaryKey val id: Int,
    val titulo: String,
    val descripcion: String,
    val imagen: String,
    val fecha_creacion: String,
    val autor: String,
    val foto_perfil: String,
    val id_usuario: Int
)
