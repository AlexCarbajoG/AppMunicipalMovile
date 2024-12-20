package com.example.appmulta.retrofit.request

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class RequestVerMulta(
    var fecMulta: LocalDate,
    var horaMulta: LocalTime,
    var lugarMulta: String,
    var distritoMulta: String,
    var placa: String,
    var marca: String,
    var modelo: String,
    var color: String,
    var estado: String,
    var año: String,
    var propietario: String,
    var telefono: String,
    var Observaciones: String,
    var montoMulta: BigDecimal,
    var estPafo: String

)
