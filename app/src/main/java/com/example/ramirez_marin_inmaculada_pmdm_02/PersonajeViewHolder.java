package com.example.ramirez_marin_inmaculada_pmdm_02;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ramirez_marin_inmaculada_pmdm_02.databinding.ItemCardviewBinding;

/**
 * ViewHolder para el RecyclerView que mantiene una referencia a las vistas de cada item
 * y maneja el enlace de los datos del personaje con esos elementos.
 */
public class PersonajeViewHolder extends RecyclerView.ViewHolder {

    // Binding que permite acceder a las vistas del item (ImageView, TextView, etc.)
    private final ItemCardviewBinding binding;

    /**
     * Constructor del ViewHolder.
     *
     * @param binding El binding que contiene las vistas del item de la lista.
     */
    public PersonajeViewHolder(ItemCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    /**
     * Este método se utiliza para enlazar los datos del personaje con las vistas del item.
     *
     * @param per El objeto PersonajeData que contiene los datos del personaje.
     */
    public void bind(PersonajeData per){
        binding.image.setImageResource(per.getImageResId());
        binding.name.setText(per.getName());
        binding.executePendingBindings(); // Asegura que se apliquen los cambios de inmediato
    }
}
