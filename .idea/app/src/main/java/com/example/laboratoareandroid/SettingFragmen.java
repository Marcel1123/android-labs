package com.example.laboratoareandroid;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class SettingFragmen extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }
}
