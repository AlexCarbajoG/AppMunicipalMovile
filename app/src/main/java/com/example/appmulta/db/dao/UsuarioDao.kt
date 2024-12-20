package com.example.appmulta.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appmulta.db.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg usuario: UsuarioEntity)

    @Update
    fun actualizar(vararg  usuario: UsuarioEntity)

    @Query("DELETE FROM usuario")
    fun eliminarTodo()

    @Query("SELECT * FROM usuario LIMIT 1")
    fun obtener(): LiveData<UsuarioEntity>
}