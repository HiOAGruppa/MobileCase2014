package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import main.mesanius.no.mobilecase2014.Kitchen.OrderActivity;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class CallCompleteOrders extends AsyncTask<String, String, String>{

    private Context context;
    public CallCompleteOrders(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String completeOrders = "completeorder";
        String JSONresult = APIManager.fetchJSON(completeOrders);

        return JSONresult;
    }

    @Override
    protected void onPostExecute(String result) {
        //start orders decrypt jsonstring

        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("orders", result);
        context.startActivity(intent);
    }
}
