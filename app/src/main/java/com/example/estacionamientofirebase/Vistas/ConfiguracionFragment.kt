package com.example.estacionamientofirebase.Vistas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.estacionamientofirebase.Estacionamiento1Activity
import com.example.estacionamientofirebase.LoginActivity
import com.example.estacionamientofirebase.MainActivity
import com.example.estacionamientofirebase.R
import com.example.estacionamientofirebase.TerminosActivity
import com.example.estacionamientofirebase.databinding.FragmentConfiguracionBinding
import com.example.estacionamientofirebase.databinding.FragmentInicioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfiguracionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfiguracionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Iniciarlizar viewBinding
    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Configuracion de viewBinding
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        val view = binding.root

        //Cambiar pantalla volver a home
        binding.imvConf1.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        //Cambiar pantalla cerrar sesión
        binding.cerrarSesion.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        //Cambiar pantalla terminos y condiciones
        binding.terminos.setOnClickListener {
            val intent = Intent(requireContext(), TerminosActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConfiguracionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConfiguracionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}