package com.example.appmulta.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appmulta.R
import com.example.appmulta.view.activities.CobroActivity
import com.example.appmulta.view.fragments.PagosFragment

class PagoMultaAdapter(
    private var listarSeriePago: List<String>,
    private val context: Context
)
    : RecyclerView.Adapter<PagoMultaAdapter.PagoMultaViewHolder>(){

     class PagoMultaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val txtpagomultas: TextView = itemView.findViewById(R.id.txtpagomultas)
         val btn_pagarmulta: Button = itemView.findViewById(R.id.btn_pagarmulta)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagoMultaAdapter.PagoMultaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pago_multa, parent, false)
        return PagoMultaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagoMultaViewHolder, position: Int) {
        val seriePago = listarSeriePago[position]
        holder.txtpagomultas.text = seriePago

        holder.btn_pagarmulta.setOnClickListener {
            // Recuperar el email desde SharedPreferences
            val sharedPref = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            val email = sharedPref.getString("USER_EMAIL", null)

            if (email != null) {
                // Iniciar CobroActivity con el email y la serie
                val intent = Intent(context, CobroActivity::class.java).apply {
                    putExtra("nroSerie", seriePago)
                    putExtra("email", email)
                }
                context.startActivity(intent)
            } else {
                // Manejar el caso donde el email no esté disponible
                Toast.makeText(context, "Error: Email no disponible", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun getItemCount(): Int = listarSeriePago.size

}