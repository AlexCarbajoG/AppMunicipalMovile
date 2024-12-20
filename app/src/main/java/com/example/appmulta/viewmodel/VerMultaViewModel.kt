package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.VerMultaRepository
import com.example.appmulta.retrofit.MultasController
import com.example.appmulta.retrofit.response.ResponseVerMultas
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class VerMultaViewModel : ViewModel() {

    private val repository = VerMultaRepository()

    private val _multasLiveData = MutableLiveData<List<ResponseVerMultas>>()
    val multasLiveData: LiveData<List<ResponseVerMultas>> get() = _multasLiveData

    fun cargarDatosMultas() {
        repository.obtenerDatosMultas(object : VerMultaRepository.MultaCallback {
            override fun onSuccess(multas: List<ResponseVerMultas>) {
                _multasLiveData.postValue(multas)
            }

            override fun onFailure(message: String) {
                // Manejar errores si es necesario
                println("Error: $message")
            }
        })
    }
}