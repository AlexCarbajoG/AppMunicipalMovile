package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.ActualizarDatosRepository
import com.example.appmulta.retrofit.request.RequestActualizarDatos
import com.example.appmulta.retrofit.response.ResponseActualizarDatos

class ActualizarDatosViewModel : ViewModel() {

    private val repository = ActualizarDatosRepository()

    val actualizarResponse: LiveData<ResponseActualizarDatos> get() = repository.actualizarResponse
    val error: LiveData<String> get() = repository.error

    fun actualizarDatos(idUsuario: Int, request: RequestActualizarDatos) {
        repository.actualizarDatos(idUsuario, request)
    }
}