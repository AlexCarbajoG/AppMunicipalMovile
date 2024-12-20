package com.example.appmulta.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appmulta.R
import com.example.appmulta.databinding.FragmentInicioBinding
import com.example.appmulta.viewmodel.UsuarioViewModel


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        usuarioViewModel = ViewModelProvider(requireActivity())[UsuarioViewModel::class.java]

        // Observa los datos del usuario desde Room
        usuarioViewModel.obtener().observe(viewLifecycleOwner, Observer { usuario ->
            usuario?.let {
                val nombreCompleto = "${it.nombreU} ${it.apellidoU}"
                binding.txtUsuario.text = nombreCompleto // Actualiza el TextView con el nombre del usuario
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}