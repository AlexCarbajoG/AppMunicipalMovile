package com.example.appmulta.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmulta.R
import com.example.appmulta.view.activities.VerMultaActivity


class MultasAdapter(private var listaSeries: List<String>)
    : RecyclerView.Adapter<MultasAdapter.MultaViewHolder>()

{
    class MultaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtserievista: TextView = itemView.findViewById(R.id.txtserievista)
        val btnVerMulta: Button = itemView.findViewById(R.id.btn_multa)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_multa, parent, false)
        return MultaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MultaViewHolder, position: Int) {
        val serie = listaSeries[position]
        holder.txtserievista.text = serie

        holder.btnVerMulta.setOnClickListener {
            val context = it.context
            val intent = Intent(context, VerMultaActivity::class.java)
            intent.putExtra("nroSerie", serie)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = listaSeries.size


}
