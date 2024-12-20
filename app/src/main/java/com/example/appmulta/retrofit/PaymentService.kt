package com.example.appmulta.retrofit

import com.example.appmulta.retrofit.response.ResponsePayment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface PaymentService {

    @PUT("estadopago/{nroSerie}")
    fun actualizarEstadoPago(
        @Path("nroSerie") nroSerie: String,
        @Body actualizarEstado: ResponsePayment
    ): Call<ResponsePayment>
}