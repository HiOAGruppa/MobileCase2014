package main.mesanius.no.mobilecase2014.API;

import android.os.AsyncTask;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class CallOrders extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        String orders = "order/";
        String JSONdata = APIManager.fetchJSON(orders);

        return JSONdata;
    }

    @Override
    protected void onPostExecute(String s) {
        //dosomething

        //send bundle to whereever orders are going.
    }
}
