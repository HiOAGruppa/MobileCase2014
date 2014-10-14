package main.mesanius.no.mobilecase2014.API;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
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
    private MenuFragment menuFragment;
    private ProgressDialog progressDialog;
    private Context context;

    public CallMenu(Context context, FragmentTransaction fragmentTransaction){
        this.fragmentTransaction = fragmentTransaction;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        menuFragment = new MenuFragment();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String menu = "menu/";
        String JSONdata = APIManager.fetchJSON(menu);
        return JSONdata;
    }

    @Override
    protected void onPostExecute(String menuItems) {

        //generate bundle for start of itemfragment
        Bundle args = new Bundle();
        args.putString("JSONMenu", menuItems);
        menuFragment.setArguments(args);

        fragmentTransaction.replace(R.id.root_frame, menuFragment);
        fragmentTransaction.commit();

        progressDialog.dismiss();

    }
}
