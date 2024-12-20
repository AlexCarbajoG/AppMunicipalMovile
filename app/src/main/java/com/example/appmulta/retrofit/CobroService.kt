package com.example.appmulta.retrofit

import android.provider.ContactsContract.CommonDataKinds.Email
import retrofit2.Call
import retrofit2.http.GET

interface CobroService {

    @GET("buscar/montoserie/{email}")
    fun listarMontoSerie(@retrofit2.http.Path("email") email: String): Call<List<String>>

}