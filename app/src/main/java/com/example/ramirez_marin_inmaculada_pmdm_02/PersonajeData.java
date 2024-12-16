package com.example.ramirez_marin_inmaculada_pmdm_02;

/**
 * Clase que representa los datos de un personaje.
 * Esta clase almacena la imagen, el nombre, la descripción y la habilidad del personaje.
 */
public class PersonajeData {
    // Atributos de la clase
    private int imageResId;   // ID del recurso de la imagen del personaje
    private String name;      // Nombre del personaje
    private String description; // Descripción del personaje
    private String ability;    // Habilidad del personaje

    /**
     * Constructor de la clase PersonajeData.
     * Inicializa los atributos del personaje con los valores proporcionados.
     *
     * @param imageResId ID del recurso de la imagen del personaje
     * @param name Nombre del personaje
     * @param description Descripción del personaje
     * @param ability Habilidad del personaje
     */
    public PersonajeData(int imageResId, String name, String description, String ability) {
        this.imageResId = imageResId;
        this.name = name;
        this.description = description;
        this.ability = ability;
    }

    /**
     * Obtiene el ID del recurso de la imagen del personaje.
     *
     * @return ID de la imagen del personaje
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Obtiene la habilidad del personaje.
     *
     * @return Habilidad del personaje
     */
    public String getAbility() {
        return ability;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return Nombre del personaje
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return Descripción del personaje
     */
    public String getDescription() {
        return description;
    }
}
