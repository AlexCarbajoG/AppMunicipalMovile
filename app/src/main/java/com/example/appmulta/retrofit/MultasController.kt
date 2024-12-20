package com.example.appmulta.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MultasController {

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://10.229.42.140:8080/api/multas/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: MultasService by lazy {
        buildRetrofit().create(MultasService::class.java)
    }
}