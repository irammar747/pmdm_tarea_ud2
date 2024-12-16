package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ramirez_marin_inmaculada_pmdm_02.databinding.PersonajeListFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Fragmento que muestra una lista de personajes en un RecyclerView.
 * Esta clase maneja la carga de datos y la configuración del RecyclerView,
 * así como la visualización de un mensaje de bienvenida al cargar la lista.
 */
public class PersonajeListFragment extends Fragment {
    private PersonajeListFragmentBinding binding; // Binding para el layout
    private ArrayList<PersonajeData> personajes; // Lista de personajes
    private PersonajeRecyclerViewAdapter adapter; // Adaptador del RecyclerView

    /**
     * Este método infla el layout para el fragmento usando el binding.
     *
     * @param inflater El inflador usado para inflar el layout.
     * @param container El contenedor en el que el fragmento se colocará.
     * @param savedInstanceState El estado guardado del fragmento, si existe.
     * @return La vista raíz inflada del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = PersonajeListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Este método se ejecuta cuando la vista del fragmento ha sido creada.
     * Aquí se inicializan los datos y se configura el RecyclerView.
     *
     * @param view La vista del fragmento que se ha creado.
     * @param savedInstanceState El estado guardado del fragmento, si existe.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de personajes
        loadData(); // Cargar los juegos (puedes implementar esta función para obtener datos)

        // Configurar el RecyclerView
        adapter = new PersonajeRecyclerViewAdapter(personajes, getActivity());
        binding.personajeRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.personajeRecyclerview.setAdapter(adapter);

        // Mostrar el Snackbar después de cargar la lista de personajes
        Snackbar.make(requireActivity().findViewById(android.R.id.content), R.string.welcome, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Método que carga los datos de los personajes y los agrega a la lista.
     * Este método crea instancias de la clase PersonajeData con datos de personajes y los agrega a la lista.
     */
    private void loadData() {
        personajes = new ArrayList<>();
        // Llenar la lista con datos de los personajes
        personajes.add(new PersonajeData(
                R.drawable.mario, // ID del recurso en drawable para Mario
                "Mario",
                getString(R.string.marioDescription),
                getString(R.string.marioAbility)
        ));

        personajes.add(new PersonajeData(
                R.drawable.luigi, // ID del recurso en drawable para Luigi
                "Luigi",
                "Hermano de Mario",
                "Saltos altos y velocidad"
        ));

        personajes.add(new PersonajeData(
                R.drawable.peach, // ID del recurso en drawable para Peach
                "Peach",
                "Princesa del Reino Champiñón",
                "Salvar el reino y ayudar a Mario"
        ));

        personajes.add(new PersonajeData(
                R.drawable.toad, // ID del recurso en drawable para Toad
                "Toad",
                "Guía del Reino Champiñón",
                "Ayudar a Mario y Peach"
        ));
    }

    /**
     * Este método se ejecuta cuando el fragmento se ha iniciado.
     * Aquí se configura el título de la ActionBar para este fragmento.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.lista_personajes);
        }
    }
}
