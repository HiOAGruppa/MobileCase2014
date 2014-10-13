package main.mesanius.no.mobilecase2014;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by NegatioN on 13.10.2014.
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
