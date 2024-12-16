package com.example.ramirez_marin_inmaculada_pmdm_02;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.Objects;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingFragment extends PreferenceFragmentCompat {

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

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.ajustes);
        }
    }
}