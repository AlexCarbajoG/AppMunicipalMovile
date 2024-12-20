package com.example.appmulta.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ActualizarController {
    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://10.229.42.140:8080/api/usuarios/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: ActualizarService by lazy {
        buildRetrofit().create(ActualizarService::class.java)
    }
}