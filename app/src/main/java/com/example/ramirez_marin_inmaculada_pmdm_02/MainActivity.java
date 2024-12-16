package com.example.ramirez_marin_inmaculada_pmdm_02;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ramirez_marin_inmaculada_pmdm_02.databinding.ActivityMainBinding;

/**
 * Actividad principal que maneja la navegación, el menú y la interacción con los fragmentos.
 * Esta actividad utiliza la barra lateral (Drawer) para la navegación entre fragmentos.
 */
public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;

    /**
     * Método que se llama al crear la actividad. Configura la vista, la navegación y los componentes.
     *
     * @param savedInstanceState Estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el NavController
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_setting)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //Para que no se muestre atrás cuando se muestra la hamburguesa
        navController.addOnDestinationChangedListener(this::onChangeView);
        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar el icono del menú en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Método que maneja los cambios de vista en la navegación.
     * Desactiva el indicador de la hamburguesa en el fragmento de detalles del personaje.
     *
     * @param navController Controlador de navegación.
     * @param navDestination Destino de navegación.
     * @param bundle Bundle con los datos.
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        if(toggle == null)return;
        toggle.setDrawerIndicatorEnabled(navDestination.getId() != R.id.personajeDetailFragment);
        toggle.syncState(); // Actualiza el estado del toggle
    }

    /**
     * Configura la navegación entre fragmentos al seleccionar elementos del menú lateral.
     */
    private void configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.nav_home); // Navegar al fragmento de inicio
            }else if(menuItem.getItemId() == R.id.nav_setting){
                navController.navigate(R.id.nav_setting);
            }
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            toggle.syncState();
            return true;
        });

    }
    /**
     * Configura el ActionBarDrawerToggle para el menú lateral.
     */
    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //setSupportActionBar(binding.toolbar); // Esto ya gestiona el ícono
    }

    /**
     * Maneja el clic en un personaje para mostrar sus detalles.
     *
     * @param personaje Objeto de tipo PersonajeData con la información del personaje.
     * @param view Vista en la que se hizo el clic.
     */
    public void personajeClicked(PersonajeData personaje, View view) {

        // Mostrar el Toast con el nombre del personaje seleccionado
        Toast.makeText(this, "Se ha seleccionado el personaje: " + personaje.getName(), Toast.LENGTH_SHORT).show();

        // Crear un Bundle para pasar los datos al PersonajeGameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", personaje.getName());
        bundle.putString("description", personaje.getDescription());
        bundle.putString("ability", personaje.getAbility());
        bundle.putInt("image", personaje.getImageResId());

        // Navegar al detalle del personaje con el Bundle
        Navigation.findNavController(view).navigate(R.id.personajeDetailFragment, bundle);
    }
    /**
     * Maneja la acción de navegación cuando se presiona el icono de la barra de acción.
     *
     * @return true si la navegación fue exitosa, false si no.
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (toggle != null) {
            toggle.syncState(); // Sincroniza el estado del toggle
        }
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = NavHostFragment.findNavController(navHostFragment);
            return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

    /**
     * Crea y muestra el menú de opciones en la ActionBar.
     *
     * @param menu Menú que se va a inflar.
     * @return true si el menú fue inflado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Maneja los clics en los elementos del menú de opciones.
     *
     * @param item Elemento del menú que fue seleccionado.
     * @return true si la acción fue manejada, false si no.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Manejar clics en el icono del menú
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if(item.getItemId() == R.id.menu_about) {
            showAboutDialog(); // Llama al método que muestra el Dialog
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Muestra un diálogo con información sobre la aplicación.
     */
    private void showAboutDialog() {
        // Crear un AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca de...")
                .setMessage("Aplicación desarrollada por Inma. Versión 1.0.")
                .setPositiveButton("OK", null) // Botón OK para cerrar el dialog
                .show();
    }
}