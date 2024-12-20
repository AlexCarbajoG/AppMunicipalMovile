package com.example.appmulta.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appmulta.R
import com.example.appmulta.databinding.ActivityRegistroBinding
import com.example.appmulta.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.btnRegistrarse.setOnClickListener(this)
        binding.button3.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    override fun onClick(view: View){
        when (view?.id){
            R.id.btn_registrarse -> registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombreU = binding.txtnombre.text.toString()
        val apellidoU = binding.txtapellido.text.toString()
        val direccion = binding.txtdireccion.text.toString()
        val telefono = binding.txttelefono.text.toString()
        val email = binding.txtemail.text.toString()
        val contraseña = binding.txtclave.text.toString()
        val confirmacionClave = binding.txtclave2.text.toString()

        if (nombreU.isEmpty() || apellidoU.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        } else if (contraseña != confirmacionClave) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        } else {
            authViewModel.registrarUsuario(nombreU, apellidoU, direccion, telefono, email, contraseña)

            authViewModel.responseRegistro.observe(this, Observer { response ->
                if (response != null) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}