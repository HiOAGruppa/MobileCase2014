package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import main.mesanius.no.mobilecase2014.Order.Order;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class PostOrder extends AsyncTask<Order, Void, String>{

    private Context context;

    public PostOrder(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Order... orders) {

        return APIManager.sendCompleteOrderJSON(orders[0]);
        //return APIManager.sendJSONOrder(order);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
