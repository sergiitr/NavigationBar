# Proyecto de Navegación con Android (Navigation Component)

Este proyecto es una aplicación de Android simple diseñada para demostrar la implementación y configuración del **Navigation Component**, utilizando una **Toolbar estándar** como barra de acción principal.
La estructura se centra en el uso de la **MainActivity** como *Navigation Host* y la integración de la navegación con el menú de la barra de herramientas.

---

## Autor
Sergio Trillo Rodriguez

## Instituto
IES Virgen del Carmen (Jaen)

---

## Enlace del video
El **Vídeo demostrativo** mostrando la aplicación funcionando en un dispositivo físico [AQUI](https://drive.google.com/file/d/1_3AKRWFU5NMYbGY3Lz-JvuO7b1UXZovP/view?usp=drive_link). 

---

## Características Clave

* **Navigation Component**: Gestión de la navegación entre destinos (*Fragments*) usando un gráfico de navegación.
* **Toolbar como ActionBar**: Uso de una Toolbar personalizada en `MainActivity` configurada como la barra de acción principal.
* **Integración de UI**: Conexión del `NavController` con la Toolbar para la automatización de títulos y el botón de navegación **Up** (flecha de retroceso).
* **Navegación por Menú**: Uso de `onOptionsItemSelected` en `MainActivity` para disparar la navegación al `HomeFragment` y `CrudFragment` desde el menú de la Toolbar.
* **View Binding**: Implementación de View Binding en los fragmentos (`HomeFragment`, `CrudFragment`) para un manejo seguro y eficiente de la vista.

---

## Estructura del Código

| Clase / Archivo                | Tipo                       | Responsabilidad Principal                                                                                                              |
| ------------------------------ | -------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| `MainActivity.kt`              | `AppCompatActivity` (Host) | Contenedor principal. Configura la Toolbar, el NavController, el AppBarConfiguration y maneja los eventos del menú para la navegación. |
| `HomeFragment.kt`              | `Fragment`                 | Pantalla de inicio de la aplicación.                                                                                                   |
| `CrudFragment.kt`              | `Fragment`                 | Destino que representa las operaciones de gestión de datos (CRUD).                                                                     |
| `HospedajeFragment.kt`         | `Fragment`                 | Destino de ejemplo (Pantalla de Hospedaje).                                                                                            |                                                                       |

---

## Configuración de la Navegación

El núcleo de la configuración reside en la **MainActivity**:

### Obtención del NavController

Se obtiene el controlador a través del `NavHostFragment`:

```kotlin
val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

navController = navHostFragment.navController
```

### Definición de Top-Level Destinations

Se utiliza `AppBarConfiguration` para especificar qué destinos **no deben mostrar** el botón de retroceso (en este caso, solo `homeFragment`):

```kotlin
appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
```

### Vinculación con la Toolbar

La línea clave que conecta el flujo de navegación con la Toolbar:

```kotlin
setupActionBarWithNavController(navController, appBarConfiguration)
```

### Soporte para Navegación Up

El método `onSupportNavigateUp` asegura que el botón de retroceso de la Toolbar funcione correctamente con la pila de navegación:

```kotlin
override fun onSupportNaviga
```
