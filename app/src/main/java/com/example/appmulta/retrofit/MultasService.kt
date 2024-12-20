package com.example.appmulta.retrofit

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.appmulta.retrofit.request.RequestMultas
import com.example.appmulta.retrofit.response.ResponseMultas
import com.example.appmulta.retrofit.response.ResponseVerMultas
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface MultasService {

    @GET("buscar/series/{email}")
    fun listarMulta(@retrofit2.http.Path("email") email: String): Call<List<String>>

    @GET("listarmultas")
    fun verMulta(): Call<List<ResponseVerMultas>>


}