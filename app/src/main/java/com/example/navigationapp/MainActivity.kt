/**
 * @author Sergio Trillo Rodriguez
 */
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

/**
 * Actividad principal de la aplicación que actúa como contenedor (Host) para la navegación.
 *
 * Esta clase configura la estructura base de la interfaz de usuario, inicializando la [Toolbar] y conectándola con el componente [NavController].
 * Se encarga de gestionar la barra de acción superior y la navegación entre los distintos fragmentos (Home, CRUD, etc.).
 */
class MainActivity : AppCompatActivity() {
    /**
     * Controlador principal de navegación. Gestiona el intercambio de contenidoen el NavHostFragment.
     */
    private lateinit var navController: NavController

    /**
     * Configuración para la barra de la aplicación. Define qué destinos se consideran de "nivel superior" (sin flecha de retroceso).
     */
    private lateinit var appBarConfiguration: AppBarConfiguration

    /**
     * Método llamado cuando la actividad se está iniciando.
     * Aquí se realizan las siguientes configuraciones críticas:
     * 1. Se establece la vista de contenido.
     * 2. Se configura la [Toolbar] personalizada como la ActionBar de la actividad.
     * 3. Se recupera el [NavController] desde el [NavHostFragment].
     * 4. Se define la [AppBarConfiguration] especificando el destino inicial (Home).
     * 5. Se vinculan el [NavController] y la [Toolbar] para automatizar el título y el botón de navegación 'Up' (retroceso).
     *
     * @param savedInstanceState Si la actividad se reinicia después de haber sido cerrada
     * por el sistema, este es el [Bundle] que contiene los datos previamente guardados.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuro la barra de herramientas (Toolbar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Obtengo el NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Configuro AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))

        // Integro NavController con la Toolbar y AppBarConfiguration
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    /**
     * Sobreescribe el método para inflar el menú de opciones en la [Toolbar].
     * @param menu El [Menu] en el que se colocan los elementos.
     * @return `true` para que el menú se muestre, `false` en caso contrario.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú de opciones (toolbar_menu); esto agrega elementos a la barra de acción si está presente
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /**
     * Sobreescribe el método para manejar la interacción del usuario con los ítems del menú de la [Toolbar].
     * Si el ítem seleccionado coincide con un ID de destino, navega al [Fragment] correspondiente usando el [NavController].
     * @param item El [MenuItem] seleccionado por el usuario.
     * @return `true` si el evento fue consumido aquí, `false` para permitir el procesamiento normal.
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
     * Habilita la navegación "Up" (hacia arriba) en la [Toolbar] (el botón de flecha de retroceso).
     * Este método es llamado cuando el usuario presiona el botón 'Up' en la barra de acción.
     * Utiliza [NavController.navigateUp] para manejar la navegación hacia atrás, respetando las reglas de la [AppBarConfiguration].
     *
     * @return `true` si el [NavController] manejó la navegación hacia arriba o `false` para realizar la acción de retroceso del sistema predeterminada.
     */
    override fun onSupportNavigateUp(): Boolean {
        // Le decimos al NavController que navegue hacia atrás en su pila de navegación, siempre que no esté en un destino Top-level.
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
