package com.example.appmulta.repository

import androidx.lifecycle.MutableLiveData
import com.example.appmulta.retrofit.CobroController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CobroRepository {

    var responseCobro = MutableLiveData<List<String>>()

    fun listarMontoSerie(email: String) : MutableLiveData<List<String>>{
        val call: Call<List<String>> = CobroController.retrofitService.listarMontoSerie(email);
        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(p0: Call<List<String>>, p1: Response<List<String>>) {
                responseCobro.value = p1.body()
            }

            override fun onFailure(p0: Call<List<String>>, p1: Throwable) {

            }


        })
        return responseCobro
    }
}