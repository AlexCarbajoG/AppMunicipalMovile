package com.example.appmulta.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmulta.R
import com.example.appmulta.retrofit.request.RequestPayment
import com.example.appmulta.view.fragments.PagosFragment
import com.example.appmulta.viewmodel.PaymentViewModel

class PaymentActivity : AppCompatActivity() {

    private lateinit var txtNumeroTarjeta: EditText
    private lateinit var txtFechaVencimiento: EditText
    private lateinit var txtCvv: EditText
    private lateinit var btnPagarTarjeta: Button
    private lateinit var btnVolver: Button

    private val paymentViewModel: PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        txtNumeroTarjeta = findViewById(R.id.txtnrotarjeta)
        txtFechaVencimiento = findViewById(R.id.txtfechavencimiento)
        txtCvv = findViewById(R.id.txtcvv)
        btnPagarTarjeta = findViewById(R.id.btnpagartarjeta)
        btnVolver = findViewById(R.id.btn_volver)

        val nroSerie = intent.getStringExtra("nroSerie") ?: ""


        btnPagarTarjeta.setOnClickListener {
            if (validarCampos()) {
                actualizarEstadoPago(nroSerie)
                limpiarCampos()
                mostrarSplash()
            }
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        val numeroTarjeta = txtNumeroTarjeta.text.toString().trim()
        val fechaVencimiento = txtFechaVencimiento.text.toString().trim()
        val cvv = txtCvv.text.toString().trim()

        // Validar número de tarjeta (16 dígitos)
        if (numeroTarjeta.length != 16 || !numeroTarjeta.all { it.isDigit() }) {
            Toast.makeText(this, "El número de tarjeta debe tener 16 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar fecha de vencimiento (MM/AAAA)
        val regexFecha = Regex("^(0[1-9]|1[0-2])/20\\d{2}$")
        if (!regexFecha.matches(fechaVencimiento)) {
            Toast.makeText(this, "La fecha debe estar en formato MM/AAAA", Toast.LENGTH_SHORT).show()
            return false
        }

        val mes = fechaVencimiento.substringBefore("/").toInt()
        val anio = fechaVencimiento.substringAfter("/").toInt()
        if (anio < 2025 || (anio == 2025 && mes <= 12)) {
            Toast.makeText(this, "La fecha de vencimiento debe ser posterior a 12/2025", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar CVV (3 dígitos)
        if (cvv.length != 3 || !cvv.all { it.isDigit() }) {
            Toast.makeText(this, "El CVV debe tener 3 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun actualizarEstadoPago(nroSerie: String){
        val request = RequestPayment(nroSerie)
        paymentViewModel.actualizarEstPago(nroSerie, request)
    }

    private fun limpiarCampos() {
        txtNumeroTarjeta.text.clear()
        txtFechaVencimiento.text.clear()
        txtCvv.text.clear()
    }

    private fun mostrarSplash(){
        val intent = Intent(this, SplashPagoTerminadoActivity::class.java)
        startActivity(intent)
        finish()
    }

}