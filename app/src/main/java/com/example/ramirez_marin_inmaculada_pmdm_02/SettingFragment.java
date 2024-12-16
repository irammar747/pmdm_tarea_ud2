package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.Objects;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Fragmento para manejar las preferencias de configuración de la aplicación, como el cambio de idioma.
 * Este fragmento proporciona la interfaz para que el usuario seleccione las preferencias y aplica el cambio de idioma.
 */
public class SettingFragment extends PreferenceFragmentCompat {

    /**
     * Método que se llama al crear las preferencias para este fragmento.
     * Se configura la preferencia de idioma y se establece un listener para manejar los cambios.
     *
     * @param savedInstanceState Estado guardado del fragmento, si es que existe.
     * @param rootKey La clave raíz de las preferencias (puede ser null).
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        // Establecer el archivo de preferencias
        setPreferencesFromResource(R.xml.preferences, rootKey);

        ListPreference languagePreference = findPreference("language");
        if(languagePreference != null){
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                // Cambiar el idioma cuando se seleccione una nueva opción
                String languageCode = (String) newValue;
                changeLanguage(languageCode);
                return true;
            });
        }
    }

    /**
     * Cambia el idioma de la aplicación de acuerdo con el código del idioma seleccionado.
     *
     * @param languageCode El código del idioma seleccionado, como "es" para español o "en" para inglés.
     */
    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);

        // Cambiar configuración del contexto
        requireActivity().getBaseContext().getResources().updateConfiguration(config, requireActivity().getBaseContext().getResources().getDisplayMetrics());

        // Reiniciar actividad para aplicar los cambios
        requireActivity().recreate();
    }

    /**
     * Método que se llama cuando el fragmento está a punto de ser visible.
     * Cambia el título del ActionBar cuando el fragmento está activo.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.ajustes);
        }
    }
}