package com.example.appmulta.view.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmulta.R
import com.example.appmulta.view.adapter.VerMultaAdapter
import com.example.appmulta.viewmodel.VerMultaViewModel

class VerMultaActivity : AppCompatActivity() {

    private lateinit var viewModel: VerMultaViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VerMultaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_multa)

        val nroSerie = intent.getStringExtra("nroSerie")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VerMultaAdapter(emptyList())
        recyclerView.adapter = adapter

        val txtSerie: TextView = findViewById(R.id.txtserievista)
        txtSerie.text = "Número de Serie : $nroSerie"

        viewModel = ViewModelProvider(this).get(VerMultaViewModel::class.java)
        viewModel.cargarDatosMultas()

        viewModel.multasLiveData.observe(this) { multas ->
            val multaFiltrada = multas.filter { it.nroSerie == nroSerie } // Filtrar por número de serie
            adapter.updateData(multaFiltrada) // Actualizar el adaptador con la multa filtrada
        }

        findViewById<Button>(R.id.btn_volvermulta).setOnClickListener {
            finish()
        }

    }
}