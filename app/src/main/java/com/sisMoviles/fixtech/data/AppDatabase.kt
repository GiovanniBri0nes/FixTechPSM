package com.sisMoviles.fixtech.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sisMoviles.fixtech.data.local.PublicacionDao
import com.sisMoviles.fixtech.data.local.PublicacionEntity
import com.sisMoviles.fixtech.data.local.UsuarioDao
import com.sisMoviles.fixtech.data.local.UsuarioEntity

@Database(
    entities = [PublicacionEntity::class, UsuarioEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun publicacionDao(): PublicacionDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fixtech-db"
                )
                    .fallbackToDestructiveMigration() // elimina y vuelve a crear la DB si cambia la versión
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

