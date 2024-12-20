package com.example.appmulta.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appmulta.databinding.ActivityLoginBinding
import com.example.appmulta.db.entity.UsuarioEntity
import com.example.appmulta.retrofit.response.ResponseLogin
import com.example.appmulta.viewmodel.AuthViewModel
import com.example.appmulta.viewmodel.UsuarioViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)

        //persistencia
        usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)
        usuarioViewModel.eliminartodo()
        //persistencia

        binding.btnAcceder.setOnClickListener {
            val email = binding.editTextText3.text.toString()
            val contraseña = binding.editTextTextPassword2.text.toString()

            if (email.isEmpty() || contraseña.isEmpty()){
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                authViewModel.autenticarUsuario(email, contraseña)

                authViewModel.responseLogin.observe(this, Observer { response ->
                    if (response != null && response.idUsuario > 0){
                        val usuario = UsuarioEntity(
                            idusuario =  response.idUsuario,
                            nombreU =  response.nombreU,
                            apellidoU =  response.apellidoU,
                            direccion =  response.direccion,
                            telefono =  response.telefono,
                            email =  response.email
                        )

                        usuarioViewModel.insertar(usuario)


                        val sharedPref = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putInt("USER_ID", response.idUsuario)
                        editor.putString("USER_EMAIL", email)
                        editor.apply()

                        // Pasar el ID como un extra al MainActivity
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra("idUsuario", response.idUsuario) // Enviar el ID del usuario
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Credenciales invalidas", Toast.LENGTH_SHORT).show()
                    }

                })

            }
        }

        binding.button.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)

        }

    }


}