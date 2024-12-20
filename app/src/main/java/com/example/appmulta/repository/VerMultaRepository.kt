package com.example.appmulta.repository


import com.example.appmulta.retrofit.MultasController
import com.example.appmulta.retrofit.response.ResponseVerMultas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerMultaRepository {

    interface MultaCallback {
        fun onSuccess(multas: List<ResponseVerMultas>)
        fun onFailure(message: String)
    }

    private val multasService = MultasController.retrofitService


    fun obtenerDatosMultas(callback: MultaCallback) {
        multasService.verMulta().enqueue(object : Callback<List<ResponseVerMultas>> {
            override fun onResponse(
                call: Call<List<ResponseVerMultas>>,
                response: Response<List<ResponseVerMultas>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onFailure("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseVerMultas>>, t: Throwable) {
                callback.onFailure("Fallo en la conexión: ${t.message}")
            }
        })
    }

}