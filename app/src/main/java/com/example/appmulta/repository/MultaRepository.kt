package com.example.appmulta.repository

import androidx.lifecycle.MutableLiveData
import com.example.appmulta.retrofit.MultasController
import com.example.appmulta.retrofit.MultasService
import com.example.appmulta.retrofit.response.ResponseMultas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MultaRepository {

    var responseMultas = MutableLiveData<List<String>>()

    fun listarMultas(email: String): MutableLiveData<List<String>>{
        val call: Call<List<String>> = MultasController.retrofitService.listarMulta(email);
        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                p0: Call<List<String>>,
                p1: Response<List<String>>
            ) {
                responseMultas.value = p1.body()
            }

            override fun onFailure(p0: Call<List<String>>, p1: Throwable) {

            }

        })
        return responseMultas
    }

}