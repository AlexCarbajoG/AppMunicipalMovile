package com.example.appmulta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appmulta.db.UsuarioRoomDatabase
import com.example.appmulta.db.entity.UsuarioEntity
import com.example.appmulta.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UsuarioRepository
    init {
        val usuarioDao = UsuarioRoomDatabase
            .getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
    }

    fun insertar(usuarioEntity: UsuarioEntity)=
        viewModelScope.launch(Dispatchers.IO){
            repository.insertar(usuarioEntity)
        }

    fun actualizar(usuarioEntity: UsuarioEntity)=
        viewModelScope.launch(Dispatchers.IO){
            repository.actualizar(usuarioEntity)
        }

    fun eliminartodo()= viewModelScope.launch(Dispatchers.IO){
        repository.eliminartodo()
    }

    fun obtener() : LiveData<UsuarioEntity>{
        return repository.obtener()
    }

}