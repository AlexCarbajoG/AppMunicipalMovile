package com.example.appmulta.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appmulta.retrofit.UsuarioController
import com.example.appmulta.retrofit.request.RequestLogin
import com.example.appmulta.retrofit.request.RequestRegistro
import com.example.appmulta.retrofit.response.ResponseLogin
import com.example.appmulta.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse = MutableLiveData<ResponseLogin>()

    fun autenticarUsuario(requestLogin: RequestLogin)
        : MutableLiveData<ResponseLogin>{
        val call: Call<ResponseLogin> = UsuarioController
            .retrofitService.login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(p0: Call<ResponseLogin>, p1: Response<ResponseLogin>) {
                loginResponse.value = p1.body()
            }

            override fun onFailure(p0: Call<ResponseLogin>, p1: Throwable) {
                Log.e("ErrorAPILogin",
                    p1.message.toString())
            }

        })
        return  loginResponse
    }

    fun registrarUsuario(requestRegistro: RequestRegistro): MutableLiveData<ResponseRegistro>{
        val registroResponse = MutableLiveData<ResponseRegistro>()
        val call: Call<ResponseRegistro> = UsuarioController.retrofitService.registro(requestRegistro)

        call.enqueue(object : Callback<ResponseRegistro>{
            override fun onResponse(call: Call<ResponseRegistro>, response: Response<ResponseRegistro>){
                if (response.isSuccessful){
                    registroResponse.value = response.body()
                }else{
                    Log.e("ErrorRegistro", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseRegistro>, t:Throwable){
                Log.e("ErrorRegistro", t.message.toString())
            }

        })

        return registroResponse

    }


}