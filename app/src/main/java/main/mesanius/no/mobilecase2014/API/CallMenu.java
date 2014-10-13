package main.mesanius.no.mobilecase2014.API;

import android.os.AsyncTask;

import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.Menu.MenuItem;

/**
 * Created by NegatioN on 13.10.2014.
 *
 * Usikker på om vi burde legge Meny-koden inni her som private class eller noe for å håndtere info
 * evnt må vi lage en load-screen herfra, og starte meny-class etterpå.
 */
public class CallMenu extends AsyncTask<String, String, ArrayList<MenuItem>> {

    @Override
    protected ArrayList<MenuItem> doInBackground(String... strings) {
        String[] empty = {""};
        String JSONdata = APIManager.fetchJSON(empty);
        return APIManager.getMenuFromString(JSONdata);
    }

    @Override
    protected void onPostExecute(ArrayList<MenuItem> menuItems) {
        //not quite sure how to handle this. Send menu through parcelable? pass into local db and do get()?


    }
}
