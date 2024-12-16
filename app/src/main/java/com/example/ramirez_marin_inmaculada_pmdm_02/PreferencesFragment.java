package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Fragmento que maneja las preferencias de la aplicación.
 * Este fragmento se encarga de cargar y gestionar las preferencias definidas en el archivo XML de preferencias.
 */
public class PreferencesFragment extends PreferenceFragmentCompat {
    /**
     * Método que se llama al crear las preferencias para este fragmento.
     *
     * @param savedInstanceState Estado guardado del fragmento, si es que existe.
     * @param rootKey La clave raíz de las preferencias (puede ser null).
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        ListPreference languagePreference = findPreference("language");
        if(languagePreference != null){
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                return true;
            });
        }
    }
}
