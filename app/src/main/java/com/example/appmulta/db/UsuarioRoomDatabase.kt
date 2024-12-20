package com.example.appmulta.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appmulta.db.dao.UsuarioDao
import com.example.appmulta.db.entity.UsuarioEntity

@Database(entities =[UsuarioEntity::class], version = 1)
abstract class UsuarioRoomDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object{

        @Volatile
        private var INSTANCIA : UsuarioRoomDatabase? = null

        fun getDatabase (context: Context): UsuarioRoomDatabase{
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioRoomDatabase::class.java,
                    "multasbd"
                ).build()
                INSTANCIA = instance
                return instance
            }
        }
    }

}