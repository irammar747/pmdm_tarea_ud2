package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ramirez_marin_inmaculada_pmdm_02.databinding.PersonajeDetailFragmentBinding;

/**
 * Fragmento que muestra los detalles de un personaje seleccionado.
 * Este fragmento se encarga de mostrar la imagen, el nombre, la descripción
 * y la habilidad de un personaje.
 */
public class PersonajeDetailFragment extends Fragment {
    private PersonajeDetailFragmentBinding binding;

    /**
     * Este método infla el layout para el fragmento.
     *
     * @param inflater El inflador que se usa para inflar el layout.
     * @param container El contenedor en el que el fragmento se va a colocar.
     * @param savedInstanceState El estado guardado del fragmento si existe.
     * @return La vista raíz del fragmento inflada.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = PersonajeDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Este método se ejecuta cuando la vista del fragmento ha sido creada.
     * Aquí se obtiene la información pasada como argumento y se asigna a las vistas.
     *
     * @param view La vista del fragmento.
     * @param savedInstanceState El estado guardado del fragmento si existe.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String ability = getArguments().getString("ability");

            // Asignar los datos a los componentes

            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.personajeDescription.setText(description);
            binding.ability.setText(ability);
        }
    }
    /**
     * Este método se ejecuta cuando el fragmento se ha iniciado.
     * Aquí se establece el título de la barra de acción (ActionBar).
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detalles_personajes);
        }
    }
}
