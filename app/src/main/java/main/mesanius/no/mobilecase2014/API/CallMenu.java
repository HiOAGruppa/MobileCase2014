package main.mesanius.no.mobilecase2014.API;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;

import main.mesanius.no.mobilecase2014.Menu.MenuFragment;
import main.mesanius.no.mobilecase2014.R;

/**
 * Created by NegatioN on 13.10.2014.
 *
 * Usikker på om vi burde legge Meny-koden inni her som private class eller noe for å håndtere info
 * evnt må vi lage en load-screen herfra, og starte meny-class etterpå.
 */
public class CallMenu extends AsyncTask<String, String, String> {

    private FragmentTransaction fragmentTransaction;

    public CallMenu(FragmentTransaction fragmentTransaction){
        this.fragmentTransaction = fragmentTransaction;
    }

    @Override
    protected String doInBackground(String... strings) {
        String menu = "menu/";
        String JSONdata = APIManager.fetchJSON(menu);
        return JSONdata;
    }

    @Override
    protected void onPostExecute(String menuItems) {
        MenuFragment menuFragment = new MenuFragment();

        //generate bundle for start of itemfragment
        Bundle args = new Bundle();
        args.putString("JSONMenu", menuItems);
        menuFragment.setArguments(args);

        fragmentTransaction.replace(R.id.menu_frame, menuFragment);
        fragmentTransaction.commit();

    }
}
