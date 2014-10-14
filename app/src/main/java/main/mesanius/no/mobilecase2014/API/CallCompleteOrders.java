package main.mesanius.no.mobilecase2014.API;

import android.os.AsyncTask;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class CallCompleteOrders extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... strings) {
        String completeOrders = "completeorder";
        String JSONresult = APIManager.fetchJSON(completeOrders);

        return JSONresult;
    }

    @Override
    protected void onPostExecute(String result) {
        //start orders decrypt jsonstring
    }
}
