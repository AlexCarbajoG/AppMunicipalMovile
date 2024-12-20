package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.MultaRepository
import com.example.appmulta.retrofit.response.ResponseMultas

class MultasViewModel : ViewModel(){

    private var repository = MultaRepository()
    var responseMultas: MutableLiveData<List<String>> = repository.responseMultas

    fun listarMultas(email: String): LiveData<List<String>>{
        return repository.listarMultas(email)
    }




}