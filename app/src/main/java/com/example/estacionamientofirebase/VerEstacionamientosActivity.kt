package com.example.estacionamientofirebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estacionamientofirebase.Adapter.AdapterEstacionamiento
import com.example.estacionamientofirebase.Models.Estacionamiento
import com.example.estacionamientofirebase.databinding.ActivityVerEstacionamientosBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerEstacionamientosActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityVerEstacionamientosBinding
    //Lista productos
    private lateinit var estacionamientosList : ArrayList<Estacionamiento>
    //Adaptador
    private lateinit var adapterEstacionamiento: AdapterEstacionamiento
    //Firebase
    private lateinit var database: DatabaseReference
    //Recicler view
    private lateinit var estacionamientoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerEstacionamientosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        estacionamientoRecyclerView = binding.rvEstacionamientos
        estacionamientoRecyclerView.layoutManager = LinearLayoutManager(this)
        estacionamientoRecyclerView.hasFixedSize()

        estacionamientosList = arrayListOf<Estacionamiento>()

        getEstacionamiento()

    }

    private fun getEstacionamiento() {

        //Ruta de los datos
        database = FirebaseDatabase.getInstance().getReference("Estacionamientos")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (estacionamientosSnapshot in snapshot.children){
                        val estacionamiento = estacionamientosSnapshot.getValue(Estacionamiento::class.java)
                        estacionamientosList.add(estacionamiento!!)
                    }
                    adapterEstacionamiento = AdapterEstacionamiento(estacionamientosList, this@VerEstacionamientosActivity)
                    estacionamientoRecyclerView.adapter = adapterEstacionamiento
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}