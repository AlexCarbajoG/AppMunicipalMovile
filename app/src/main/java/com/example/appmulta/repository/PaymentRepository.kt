package com.example.appmulta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appmulta.retrofit.PaymentController
import com.example.appmulta.retrofit.request.RequestPayment
import com.example.appmulta.retrofit.response.ResponsePayment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentRepository {

    private val _paymentResponse = MutableLiveData<ResponsePayment>()
    val paymentResponse: LiveData<ResponsePayment> get() = _paymentResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun actualizarEstado(nroSerie: String, request: RequestPayment){
        PaymentController.retrofitService.actualizarEstadoPago(nroSerie, ResponsePayment("P"))
            .enqueue(object : Callback<ResponsePayment> {
                override fun onResponse(p0: Call<ResponsePayment>, p1: Response<ResponsePayment>) {
                    if (p1.isSuccessful){
                        _paymentResponse.postValue(p1.body())
                    }else {
                        _error.postValue("Error: ${p1.code()} - ${p1.message()}")
                    }

                }

                override fun onFailure(p0: Call<ResponsePayment>, t: Throwable) {
                    _error.postValue("Fallo en la conexión: ${t.message}")
                }


            })
    }

}