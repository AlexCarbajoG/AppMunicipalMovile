package com.example.appmulta.retrofit

import com.example.appmulta.retrofit.request.RequestActualizarDatos
import com.example.appmulta.retrofit.response.ResponseActualizarDatos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ActualizarService {
    @PUT("datos/{id_Usuario}")
    fun actualizar(
        @Path("id_Usuario") idUsuario: Int,
        @Body actualizarDatos: RequestActualizarDatos
    ): Call<ResponseActualizarDatos>
}