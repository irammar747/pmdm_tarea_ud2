package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ramirez_marin_inmaculada_pmdm_02.databinding.ItemCardviewBinding;

import java.util.ArrayList;

/**
 * Adaptador para el RecyclerView que muestra una lista de personajes.
 * Se encarga de inflar la vista de cada item, bindear los datos de los personajes
 * y gestionar los clics en los elementos de la lista.
 */
public class PersonajeRecyclerViewAdapter extends RecyclerView.Adapter<PersonajeViewHolder> {
    // Lista de objetos PersonajeData que contienen los datos de los personajes
    private final ArrayList<PersonajeData> personajes;
    // Contexto de la actividad en la que se está utilizando el RecyclerView
    private final Context context;

    /**
     * Constructor del adaptador.
     *
     * @param personajes Lista de personajes que se va a mostrar.
     * @param context El contexto de la actividad que utiliza este adaptador.
     */
    public PersonajeRecyclerViewAdapter(ArrayList<PersonajeData> personajes, Context context) {
        this.personajes = personajes;
        this.context = context;
    }

    /**
     * Este método se llama cuando se necesita crear una nueva vista para un item en el RecyclerView.
     *
     * @param parent El contenedor en el que se insertará la nueva vista.
     * @param viewType El tipo de vista (no se utiliza en este caso).
     * @return Un nuevo ViewHolder que contiene la vista inflada.
     */
    @NonNull
    @Override
    public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardviewBinding binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonajeViewHolder(binding);
    }

    /**
     * Este método se llama para enlazar los datos con la vista de un item en el RecyclerView.
     *
     * @param holder El ViewHolder que contiene la vista del item.
     * @param position La posición del item en la lista de datos.
     */
    @Override
    public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {
        PersonajeData currentPer = this.personajes.get(position);
        holder.bind(currentPer);

        //Manejar el evento de clic
        holder.itemView.setOnClickListener(view -> itemClicked(currentPer, view));
    }

    /**
     * Método que maneja el clic en un item de la lista de personajes.
     *
     * @param currentPer El personaje que ha sido clicado.
     * @param view La vista que fue clicada.
     */
    private void itemClicked(PersonajeData currentPer, View view) {
        // Llama a la función gameClicked de MainActivity, pasando la vista
        ((MainActivity) context).personajeClicked(currentPer, view);
    }

    /**
     * Este método devuelve el número total de items en la lista de personajes.
     *
     * @return El número de personajes en la lista.
     */
    @Override
    public int getItemCount() {
        return this.personajes.size();
    }

}
