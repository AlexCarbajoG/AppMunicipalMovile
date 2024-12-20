package com.example.appmulta.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appmulta.R
import com.example.appmulta.viewmodel.CobroViewModel

class CobroActivity : AppCompatActivity() {

    private lateinit var txtSerieCobro: TextView
    private lateinit var txtMontoCobro: TextView
    private lateinit var btnCobrar: Button
    private lateinit var cobroViewModel: CobroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro)

        txtSerieCobro = findViewById(R.id.txtseriecobromulta)
        txtMontoCobro = findViewById(R.id.txtseriecobromulta2)
        btnCobrar = findViewById(R.id.btn_cobromulta)

        cobroViewModel = ViewModelProvider(this).get(CobroViewModel::class.java)

        val emailUsuario = intent.getStringExtra("email")
        val serieMulta = intent.getStringExtra("nroSerie")

        if (emailUsuario != null && serieMulta != null ){
            txtSerieCobro.text = serieMulta
            obtenerMontoDesdeApi(emailUsuario, serieMulta)
        }else{
            Toast.makeText(this, "Error: Datos incompletos", Toast.LENGTH_SHORT).show()
        }

        btnCobrar.setOnClickListener {
            // Validar si se tiene un monto antes de continuar
            val montoCobro = txtMontoCobro.text.toString()
            if (montoCobro.isNotEmpty() && montoCobro != "No encontrado") {
                // Redirigir a PaymentActivity
                val intent = Intent(this, PaymentActivity::class.java).apply {
                    putExtra("monto", montoCobro)
                    putExtra("nroSerie", txtSerieCobro.text.toString())
                }
                startActivity(intent)
                finish() // Finalizar esta actividad
            } else {
                Toast.makeText(this, "Error: No se puede procesar el pago", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun obtenerMontoDesdeApi(email: String, serie: String) {
        cobroViewModel.listarMontoSerie(email).observe(this){ response ->
            val multaEncontrada = response.find { it.startsWith(("Serie: $serie")) }
            if (multaEncontrada != null) {
                val monto = multaEncontrada.substringAfter("Monto: ").trim()
                txtMontoCobro.text = monto
            }else{
                txtMontoCobro.text = "No encontrado"
                Toast.makeText(this, "No se encontró el monto de la multa", Toast.LENGTH_SHORT).show()

            }

        }

    }
}