package com.example.estacionamientofirebase.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.estacionamientofirebase.Models.Estacionamiento
import androidx.recyclerview.widget.RecyclerView
import com.example.estacionamientofirebase.Estacionamiento1Activity
import com.example.estacionamientofirebase.R

class AdapterEstacionamiento(private var estacionamientos: ArrayList<Estacionamiento>,  private val context: Context) :
RecyclerView.Adapter<AdapterEstacionamiento.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val estado: TextView = itemView.findViewById(R.id.tvEstado)
        val btnVolver: Button = itemView.findViewById(R.id.btnVolver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estacionamientos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return estacionamientos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estacionamiento = estacionamientos[position]

        holder.nombre.text = estacionamiento.nombre
        holder.estado.text = estacionamiento.estado

        //Btn Volver
        holder.btnVolver.setOnClickListener {
            val intent = Intent(context, Estacionamiento1Activity::class.java)
            context.startActivity(intent)
        }
    }

}