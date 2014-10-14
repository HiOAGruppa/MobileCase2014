package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by NegatioN on 14.10.2014.
 */

//må teste delete mer for å se om den funker.
public class DeleteOrder extends AsyncTask<Integer, Void, String> {


    private Context context;

    public DeleteOrder(){

    }

    @Override
    protected String doInBackground(Integer... integers) {

        return APIManager.deleteCompleteOrder(integers[0]) + "";
    }

    @Override
    protected void onPostExecute(String result) {

    }
}
