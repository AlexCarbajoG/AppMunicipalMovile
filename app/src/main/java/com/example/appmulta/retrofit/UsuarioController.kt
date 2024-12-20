package com.example.appmulta.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UsuarioController {

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://10.229.42.140:8080/api/usuarios/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: UsuarioService by lazy {
        buildRetrofit().create(UsuarioService::class.java)
    }
}