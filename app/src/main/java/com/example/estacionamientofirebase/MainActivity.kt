package com.example.estacionamientofirebase

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estacionamientofirebase.Vistas.AcercadeFragment
import com.example.estacionamientofirebase.Vistas.ConfiguracionFragment
import com.example.estacionamientofirebase.Vistas.InicioFragment
import com.example.estacionamientofirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Inicializar viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configurar viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentToShow = intent.getStringExtra("FRAGMENT_TO_SHOW")
        if (fragmentToShow == "InicioFragment") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment())
                .commit()
        }


        //Cargar fragment por defecto
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, InicioFragment()).commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, InicioFragment()).commit()
                    true
                }
                R.id.item2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AcercadeFragment()).commit()
                    true
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ConfiguracionFragment()).commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    true
                }
                R.id.item2 -> {
                    true
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ConfiguracionFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}