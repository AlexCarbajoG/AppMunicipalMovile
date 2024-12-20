package com.example.appmulta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity (

    @PrimaryKey val idusuario: Int,
    val nombreU: String?,
    val apellidoU: String?,
    val direccion: String?,
    val telefono: String?,
    val email: String?

)

