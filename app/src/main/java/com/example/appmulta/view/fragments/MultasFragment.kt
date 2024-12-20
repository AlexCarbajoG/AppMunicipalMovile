package com.example.appmulta.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmulta.databinding.FragmentMultasBinding
import com.example.appmulta.view.adapter.MultasAdapter
import com.example.appmulta.viewmodel.MultasViewModel


class MultasFragment : Fragment() {

    private var _binding: FragmentMultasBinding? = null
    private val binding get() = _binding!!
    private lateinit var multasViewModel: MultasViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMultasBinding.inflate(inflater, container, false)


        binding.rvmultas.layoutManager = LinearLayoutManager(requireActivity())
        multasViewModel = ViewModelProvider(requireActivity()).get(MultasViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val emailUsuario = sharedPref.getString("USER_EMAIL", "")

        if (!emailUsuario.isNullOrEmpty()){
            println("Email del usuario logueado: $emailUsuario") // Debugging
            listarmultas(emailUsuario)
        }else{
            println("No se encontró el email del usuario.")
        }

        return binding.root
    }

    fun listarmultas(email: String){
        multasViewModel.listarMultas(email).observe(
            viewLifecycleOwner, Observer {
                listaSeries ->
                if (listaSeries.isEmpty()) {
                    // Mostrar mensaje si no hay multas
                    Toast.makeText(requireContext(), "Usted no tiene multas registradas.", Toast.LENGTH_SHORT).show()
                } else {
                    // Mostrar las multas
                    binding.rvmultas.adapter = MultasAdapter(listaSeries)
                }
            }
        )

    }


}