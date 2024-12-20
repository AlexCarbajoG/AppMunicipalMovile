package com.example.appmulta.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmulta.repository.PagoMultaRepository

class PagosMultaViewModel : ViewModel(){

    private var repository = PagoMultaRepository()
    var responsePagoMulta : MutableLiveData<List<String>> = repository.responsePagoMulta

    fun listaDePagos(email: String): LiveData<List<String>>{
        return repository.listarPago(email)
    }
}