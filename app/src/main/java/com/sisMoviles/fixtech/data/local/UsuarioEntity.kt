package com.sisMoviles.fixtech.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val telefono: String,
    val foto_perfil: String,
    val nickname: String
)
