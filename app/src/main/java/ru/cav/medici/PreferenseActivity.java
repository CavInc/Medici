package ru.cav.medici;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenseActivity extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
