package com.example.appmulta.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appmulta.R
import com.example.appmulta.databinding.FragmentActualizarDatosBinding
import com.example.appmulta.retrofit.request.RequestActualizarDatos
import com.example.appmulta.viewmodel.ActualizarDatosViewModel

class ActualizarDatosFragment : Fragment() {

    private lateinit var binding: FragmentActualizarDatosBinding
    private lateinit var viewModel: ActualizarDatosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActualizarDatosBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ActualizarDatosViewModel::class.java]

        // Obtener el ID del usuario desde los argumentos
        val idUsuario = arguments?.getInt("idUsuario", -1) ?: -1

        // Validar el ID y mostrarlo en el TextView
        if (idUsuario != -1) {
            binding.tvIdUsuario.text = "ID: $idUsuario"
        } else {
            binding.tvIdUsuario.text = "ID no disponible"
        }

        binding.btnActualizarDatos.setOnClickListener {
            if (idUsuario == -1) {
                Toast.makeText(requireContext(), "ID de usuario inválido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar que los campos no estén vacíos
            if (binding.actnombre.text.isNullOrEmpty() ||
                binding.actapellido.text.isNullOrEmpty() ||
                binding.actdireccion.text.isNullOrEmpty() ||
                binding.acttelefono.text.isNullOrEmpty() ||
                binding.actemail.text.isNullOrEmpty()) {

                Toast.makeText(requireContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Construir la petición
            val request = RequestActualizarDatos(
                nombreU = binding.actnombre.text.toString(),
                apellidoU = binding.actapellido.text.toString(),
                direccion = binding.actdireccion.text.toString(),
                telefono = binding.acttelefono.text.toString(),
                email = binding.actemail.text.toString()
            )

            // Llamar al ViewModel
            viewModel.actualizarDatos(idUsuario, request)
        }

        viewModel.actualizarResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), "Datos actualizados: ${response.nombreU}", Toast.LENGTH_SHORT).show()

            // Obtener el ID del usuario actual desde los argumentos
            val idUsuario = arguments?.getInt("idUsuario", -1) ?: -1

            // Crear un Bundle con el ID del usuario
            val bundle = Bundle().apply {
                putInt("idUsuario", idUsuario)
            }

            // Recargar el mismo fragmento con el ID del usuario
            val navController = findNavController()
            navController.popBackStack() // Eliminar el fragmento actual del stack
            navController.navigate(R.id.actualizarDatosFragment, bundle) // Volver a cargar el fragmento con el ID

        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}