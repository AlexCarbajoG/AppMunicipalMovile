package com.example.appmulta.repository

import androidx.lifecycle.LiveData
import com.example.appmulta.db.dao.UsuarioDao
import com.example.appmulta.db.entity.UsuarioEntity

class UsuarioRepository (private val usuarioDao: UsuarioDao){

     suspend fun insertar(usuarioEntity: UsuarioEntity){
         usuarioDao.insertar(usuarioEntity)
     }

    suspend fun actualizar(usuarioEntity: UsuarioEntity){
        usuarioDao.actualizar(usuarioEntity)
    }

    suspend fun eliminartodo(){
        usuarioDao.eliminarTodo()
    }

    fun obtener(): LiveData<UsuarioEntity>{
        return usuarioDao.obtener()
    }

}