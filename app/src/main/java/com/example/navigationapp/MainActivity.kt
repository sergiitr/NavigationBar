package com.example.navigationapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configurar la barra de herramientas (Toolbar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // [cite: 541]

        // 2. Obtener el NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment // [cite: 536]
        navController = navHostFragment.navController // [cite: 538]

        // 3. Configurar AppBarConfiguration (Punto 3)
        // Definimos 'homeFragment' como el único destino Top-level (nivel superior).
        // En los destinos Top-level, no aparece el botón de retroceso.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment)) // [cite: 728]

        // 4. Integrar NavController con la Toolbar y AppBarConfiguration
        // Esto se encarga de:
        // a) Mostrar/Ocultar el botón de navegación (retroceso) según el destino sea o no Top-level.
        // b) Cambiar automáticamente el título de la Toolbar (usa el label del fragmento del nav_graph). [cite: 688]
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // 5. Sobreescribir onCreateOptionsMenu para inflar el menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú de opciones; esto agrega elementos a la barra de acción si está presente. [cite: 546]
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // 6. Sobreescribir onOptionsItemSelected para manejar la interacción con los items del menú (Punto 2)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Controlar los eventos de los items del toolbar [cite: 560]
        return when (item.itemId) {
            R.id.homeFragment -> {
                navController.navigate(R.id.homeFragment) // [cite: 564]
                true
            }
            R.id.crudFragment -> {
                navController.navigate(R.id.crudFragment) // [cite: 568]
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // 7. Habilitar la navegación hacia arriba (Botón de retroceso de la Toolbar) (Punto 3)
    override fun onSupportNavigateUp(): Boolean {
        // Le dice al NavController que navegue hacia atrás en su pila de navegación, siempre que no esté en el destino inicial. [cite: 726]
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp() // [cite: 731]
    }
}