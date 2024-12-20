package com.example.appmulta.retrofit

import com.example.appmulta.retrofit.response.ResponseVerMultas
import retrofit2.Call
import retrofit2.http.GET

interface PagoMultaService {

    @GET("buscar/series/{email}")
    fun listarMultaPago(@retrofit2.http.Path("email") email: String): Call<List<String>>

    @GET("listarmultas")
    fun verMultaPago(): Call<List<ResponseVerMultas>>

}