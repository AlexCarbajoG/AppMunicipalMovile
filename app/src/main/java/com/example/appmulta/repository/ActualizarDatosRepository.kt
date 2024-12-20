package com.example.appmulta.repository

import com.example.appmulta.retrofit.ActualizarController
import com.example.appmulta.retrofit.ActualizarService
import com.example.appmulta.retrofit.request.RequestActualizarDatos
import com.example.appmulta.retrofit.response.ResponseActualizarDatos
import retrofit2.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import retrofit2.Response

class ActualizarDatosRepository {

    private val actualizarService = ActualizarController.retrofitService


    private val _actualizarResponse = MutableLiveData<ResponseActualizarDatos>()
    val actualizarResponse: LiveData<ResponseActualizarDatos> get() = _actualizarResponse


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun actualizarDatos(idUsuario: Int, request : RequestActualizarDatos) {
        ActualizarController.retrofitService.actualizar(idUsuario, request).enqueue(object : Callback<ResponseActualizarDatos> {
            override fun onResponse(call: Call<ResponseActualizarDatos>, response: Response<ResponseActualizarDatos>) {
                if (response.isSuccessful) {
                    _actualizarResponse.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseActualizarDatos>, t: Throwable) {
                _error.postValue("Fallo en la conexión: ${t.message}")
            }

        })
    }
}
