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
import com.example.appmulta.R
import com.example.appmulta.databinding.FragmentPagosBinding
import com.example.appmulta.view.adapter.MultasAdapter
import com.example.appmulta.view.adapter.PagoMultaAdapter
import com.example.appmulta.viewmodel.PagosMultaViewModel


class PagosFragment : Fragment() {

    private var _binding: FragmentPagosBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagosMultaViewModel: PagosMultaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPagosBinding.inflate(inflater, container, false)

        binding.rvmutalpago.layoutManager = LinearLayoutManager(requireActivity())
        pagosMultaViewModel = ViewModelProvider(requireActivity()).get(PagosMultaViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val emailUsuario = sharedPref.getString("USER_EMAIL","")

        if (!emailUsuario.isNullOrEmpty()){
            println("Email del usuario logueado: $emailUsuario")
            listarPagoDeMultas(emailUsuario)
        }else{
            println("No se encontro el email del usuario")
        }

        return binding.root

    }

    private fun listarPagoDeMultas(email: String) {
        pagosMultaViewModel.listaDePagos(email).observe(
            viewLifecycleOwner, Observer {
                listarSeriePago ->
                if (listarSeriePago.isEmpty()){
                    Toast.makeText(requireContext(), "Usted no tiene multas a pagar.",Toast.LENGTH_SHORT).show()
                }else{
                    binding.rvmutalpago.adapter = PagoMultaAdapter(listarSeriePago, requireContext())
                }
            }
        )

    }


}