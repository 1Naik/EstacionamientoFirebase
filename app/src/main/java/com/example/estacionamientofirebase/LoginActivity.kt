package com.example.estacionamientofirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estacionamientofirebase.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    //Inicialización de viewBinding
    private lateinit var binding : ActivityLoginBinding

    // Configuración de Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configuración de viewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar firebase
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            // Recuperar el correo y la clave
            val correo = binding.etEmail.text.toString()
            val clave = binding.etPassword.text.toString()

            if (correo.isEmpty()){
                binding.etEmail.error = "Por favor ingrese un correo"
                return@setOnClickListener
            }
            if (clave.isEmpty()){
                binding.etPassword.error = "Por favor ingrese la constraseña"
                return@setOnClickListener
            }
            signUp(correo, clave)
        }
        binding.tvNuevaCuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}