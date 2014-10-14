package main.mesanius.no.mobilecase2014;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by marhag on 14.10.14.
 */
public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
