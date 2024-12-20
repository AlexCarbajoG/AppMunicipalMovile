package com.example.appmulta.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appmulta.R
import com.example.appmulta.databinding.ActivityMainBinding
import com.example.appmulta.viewmodel.UsuarioViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idUsuario = intent.getIntExtra("idUsuario", -1) // Valor por defecto -1 si no existe

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.inicioFragment, R.id.multasFragment, R.id.pagosFragment,
                R.id.actualizarDatosFragment,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val headerView: View = navView.getHeaderView(0)
        val tvNombreUsuario: TextView = headerView.findViewById(R.id.tvnomusuario)
        val tvEmailUsuario: TextView = headerView.findViewById(R.id.tvemailusuario)

        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.obtener().observe(this) { usuario ->
            if (usuario != null) {
                tvNombreUsuario.text = "${usuario.nombreU} ${usuario.apellidoU}" // Nombre completo
                tvEmailUsuario.text = usuario.email // Email del usuario
            }
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            val bundle = Bundle().apply {
                putInt("idUsuario", idUsuario) // Agregar el ID del usuario al bundle
            }

            when(menuItem.itemId){
                R.id.salir -> {
                    mostrarDialogoSalir()
                    true
                }
                R.id.actualizarDatosFragment -> {
                    // Navegar al ActualizarDatosFragment con el ID del usuario
                    navController.navigate(R.id.actualizarDatosFragment, bundle)
                    drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    menuItem.isChecked = true
                    navController.navigate(menuItem.itemId)
                    drawerLayout.closeDrawers()
                    true
                }
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        if (idItem == R.id.action_settings){
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun mostrarDialogoSalir(){
        AlertDialog.Builder(this).apply {
            setTitle("Confirmar salida")
            setMessage("¿Estas seguro de que deseas salir de la aplicación?")
            setPositiveButton("Salir") { _, _ ->
                finishAffinity()
            }
            setNegativeButton("Cancelar"){ dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }

}