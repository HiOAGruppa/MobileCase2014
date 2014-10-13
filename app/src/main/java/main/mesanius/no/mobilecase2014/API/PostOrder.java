package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import main.mesanius.no.mobilecase2014.Order.OrderItem;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class PostOrder extends AsyncTask<String, Void, String>{

    private Context context;

    public PostOrder(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        //creates new test-orderitem of quantity 1, itemid 2
        OrderItem order = new OrderItem(2, 1);



        return APIManager.sendJSONOrder(order);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
