package main.mesanius.no.mobilecase2014;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.mesanius.no.mobilecase2014.API.CallMenu;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class MenuFrame extends Fragment {

    public MenuFrame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate PageFrame Layout som holder andre fragments
        View view = inflater.inflate(R.layout.menu_frame, container, false);

        //Bytte ut det tomme Layoutet i PageFrame med MenyFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //get menu from API and generate menufragment
        new CallMenu(transaction).execute("menugen");

        return view;
    }
}
