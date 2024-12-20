package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.AuthRepository
import com.example.appmulta.retrofit.request.RequestLogin
import com.example.appmulta.retrofit.request.RequestRegistro
import com.example.appmulta.retrofit.response.ResponseLogin
import com.example.appmulta.retrofit.response.ResponseRegistro

class AuthViewModel : ViewModel(){

    var responseLogin: LiveData<ResponseLogin>

    private var repository = AuthRepository()

    init {
        responseLogin = repository.loginResponse
    }

    fun autenticarUsuario(email: String, password: String){
        responseLogin = repository.autenticarUsuario(
            RequestLogin(email, password)
        )
    }

    var responseRegistro: LiveData<ResponseRegistro> = MutableLiveData()

    fun registrarUsuario(
        nombreU: String,
        apellidoU: String,
        direccion: String,
        telefono: String,
        email: String,
        contraseña: String
    ){
        responseRegistro = repository.registrarUsuario(
            RequestRegistro(nombreU, apellidoU, direccion, telefono, email, contraseña)
        )
    }

}