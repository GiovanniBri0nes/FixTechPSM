package com.sisMoviles.fixtech.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PublicacionDao {

    @Query("SELECT * FROM publicaciones ORDER BY fecha_creacion DESC")
    suspend fun obtenerTodas(): List<PublicacionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(publicaciones: List<PublicacionEntity>)

    @Query("DELETE FROM publicaciones")
    suspend fun eliminarTodas()
}
