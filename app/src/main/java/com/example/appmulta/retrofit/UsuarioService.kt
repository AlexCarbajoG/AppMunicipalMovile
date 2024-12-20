package com.example.appmulta.retrofit

import com.example.appmulta.retrofit.request.RequestActualizarDatos
import com.example.appmulta.retrofit.request.RequestLogin
import com.example.appmulta.retrofit.request.RequestRegistro
import com.example.appmulta.retrofit.response.ResponseActualizarDatos
import com.example.appmulta.retrofit.response.ResponseLogin
import com.example.appmulta.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface UsuarioService {

    @POST("login/authenticate")
    fun login(@Body loginRequest : RequestLogin): Call<ResponseLogin>

    @POST("registrar")
    fun registro(@Body registroRequest: RequestRegistro): Call<ResponseRegistro>


}