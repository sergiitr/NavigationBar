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

        // 1. Configuramos la barra de herramientas (Toolbar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 2. Obtenemos el NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 3. Configuramos AppBarConfiguration (Punto 3)
        // Definimos 'homeFragment' como el único destino Top-level (nivel superior).
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))

        // 4. Integrar NavController con la Toolbar y AppBarConfiguration
        // Esto se encarga de:
        // a) Mostrar/Ocultar el botón de navegación (retroceso) según el destino sea o no Top-level.
        // b) Cambiar automáticamente el título de la Toolbar.
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    
    /**
     * Sobreescribimos onCreateOptionsMenu para inflar el menú
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú de opciones; esto agrega elementos a la barra de acción si está presente
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /**
     * Sobreescribimos onOptionsItemSelected para manejar la interacción con los items del menú
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Controlamos los eventos de los items del toolbar
        return when (item.itemId) {
            R.id.homeFragment -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.crudFragment -> {
                navController.navigate(R.id.crudFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Habilitamos la navegación hacia arriba (Botón de retroceso de la Toolbar)
     */
    override fun onSupportNavigateUp(): Boolean {
        // Le decimos al NavController que navegue hacia atrás en su pila de navegación, siempre que no esté en el destino inicial.
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
