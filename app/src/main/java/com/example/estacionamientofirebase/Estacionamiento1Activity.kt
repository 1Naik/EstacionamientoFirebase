package com.example.estacionamientofirebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estacionamientofirebase.Models.Estacionamiento
import com.example.estacionamientofirebase.databinding.ActivityEstacionamiento1Binding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Estacionamiento1Activity : AppCompatActivity() {

    //Activar firebase
    private lateinit var database: DatabaseReference

    //Inicializar binding
    private lateinit var binding: ActivityEstacionamiento1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configurar viewBinding
        binding = ActivityEstacionamiento1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Iniciar la base de datos
        database = FirebaseDatabase.getInstance().getReference("Estacionamientos")

        binding.btnAgregar.setOnClickListener {
            //Obtener la data del formulario

            val nombre = binding.etEstacionamiento.text.toString()
            val estado = binding.etEstado.text.toString()
            //Generar un id unico
            val id = database.child("Estacionamientos").push().key

            if(nombre.isEmpty()){
                binding.etEstacionamiento.error = "Por favor ingresar estacionamiento"
                return@setOnClickListener
            }

            if(estado.isEmpty()){
                binding.etEstado.error = "Por favor ingresar un estado"
                return@setOnClickListener
            }

            val estacionamiento = Estacionamiento(id, nombre, estado)

            database.child(id!!).setValue(estacionamiento)
                .addOnSuccessListener {
                    binding.etEstado.setText("")
                    binding.etEstacionamiento.setText("")
                    Snackbar.make(binding.root, "Estacionamiento Agregado", Snackbar.LENGTH_SHORT)
                        .show()
                }

        }

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnVer.setOnClickListener {
            val intent = Intent(this, VerEstacionamientosActivity::class.java)
            startActivity(intent)
        }


    }
}