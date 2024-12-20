package com.example.appmulta.retrofit.response

data class ResponsePagoMulta(
    val multas: List<Multa>
)

data class MultaPago(
    val nroSerie: String,
    val estPago: String

    )