package com.example.appmulta.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmulta.R
import com.example.appmulta.retrofit.response.ResponseVerMultas

class VerMultaAdapter (private var multaList: List<ResponseVerMultas>):
    RecyclerView.Adapter<VerMultaAdapter.VerMultaViewHolder>() {

        class VerMultaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val txtFecha: TextView = itemView.findViewById(R.id.txtFecha)
            val txtHora: TextView = itemView.findViewById(R.id.txthoramulta)
            val txtLugar: TextView = itemView.findViewById(R.id.txtlugarmulta)
            val txtDistrito: TextView = itemView.findViewById(R.id.txtdistrito)
            val txtPlaca: TextView = itemView.findViewById(R.id.txtplaca)
            val txtMarca: TextView = itemView.findViewById(R.id.txtmarcavehiculo)
            val txtModelo: TextView = itemView.findViewById(R.id.txtmodelo)
            val txtColor: TextView = itemView.findViewById(R.id.txtcolor)
            val txtEstado: TextView = itemView.findViewById(R.id.txtestado)
            val txtAnio: TextView = itemView.findViewById(R.id.txtañovehiculo)
            val txtPropietario: TextView = itemView.findViewById(R.id.txtpropietario)
            val txtTelefono: TextView = itemView.findViewById(R.id.txttelefonomulta)
            val txtObservaciones: TextView = itemView.findViewById(R.id.textView18)
            val txtMonto: TextView = itemView.findViewById(R.id.txtmontomulta)
            val txtEstPago: TextView = itemView.findViewById(R.id.txtestpago)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerMultaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ver_multa, parent, false)
        return VerMultaViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerMultaViewHolder, position: Int) {
        val multa = multaList[position]



        holder.txtFecha.text = multa.fecMulta
        holder.txtHora.text = multa.horaMulta
        holder.txtLugar.text = "Lugar: ${multa.lugarMulta}"
        holder.txtDistrito.text = "Distrito: ${multa.distritoMulta}"
        holder.txtPlaca.text = "Placa: ${multa.placa}"
        holder.txtMarca.text = "Marca: ${multa.marca}"
        holder.txtModelo.text = "Modelo: ${multa.modelo}"
        holder.txtColor.text = "Color: ${multa.color}"
        holder.txtEstado.text = "Estado: ${multa.estado}"
        holder.txtAnio.text = "Año: ${multa.año.toString()}"
        holder.txtPropietario.text = "Propietario: ${multa.propietario}"
        holder.txtTelefono.text = "Telefono: ${multa.telefono.toString()}"
        holder.txtObservaciones.text = "Observaciones: ${multa.observaciones}"
        holder.txtMonto.text = "S/. ${multa.montoMulta}"
        val estadoPago = if (multa.estPago == "NP") "NO PAGADO" else if (multa.estPago == "P") "PAGADO" else "DESCONOCIDO"
        holder.txtEstPago.text = estadoPago
    }

    override fun getItemCount(): Int = multaList.size

    fun updateData(newMultas: List<ResponseVerMultas>){
        multaList = newMultas
        notifyDataSetChanged()
    }

}