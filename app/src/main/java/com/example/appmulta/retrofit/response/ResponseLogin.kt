package com.example.appmulta.retrofit.response

import java.util.stream.Stream

data class ResponseLogin(
    var idUsuario: Int,
    var nombreU: String,
    var apellidoU: String,
    var direccion: String,
    var telefono: String,
    var email: String,

)
