package com.example.appmulta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.CobroRepository

class CobroViewModel : ViewModel() {
    private var repository = CobroRepository()
    var responseCobro: MutableLiveData<List<String>> = repository.responseCobro

    fun listarMontoSerie(email: String): LiveData<List<String>>{
        return repository.listarMontoSerie(email)
    }
}