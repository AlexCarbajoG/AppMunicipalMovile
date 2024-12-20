package com.example.appmulta.retrofit.response

data class ResponseCobro(
    var multas: List<Multa>
)

data class CobroMulta(
    val nroSerie: String,
    val montoMulta: String
)
