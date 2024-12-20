package com.example.appmulta.repository

import androidx.lifecycle.MutableLiveData
import com.example.appmulta.retrofit.PagoMultasController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagoMultaRepository {

    var responsePagoMulta = MutableLiveData<List<String>>()

    fun listarPago(email: String): MutableLiveData<List<String>>{
        val call: Call<List<String>> = PagoMultasController.retrofitService.listarMultaPago(email);
        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(p0: Call<List<String>>, p1: Response<List<String>>) {
                responsePagoMulta.value = p1.body()
            }

            override fun onFailure(p0: Call<List<String>>, p1: Throwable) {

            }

        })
        return responsePagoMulta
    }
}