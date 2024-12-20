package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.PaymentRepository
import com.example.appmulta.retrofit.request.RequestPayment
import com.example.appmulta.retrofit.response.ResponsePayment

class PaymentViewModel : ViewModel(){

    private val repository = PaymentRepository()

    val actualizarEstado: LiveData<ResponsePayment> get() = repository.paymentResponse
    val error: LiveData<String> get() = repository.error

    fun actualizarEstPago(nroSerie: String, request: RequestPayment){
        repository.actualizarEstado(nroSerie, request)
    }
}