package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by NegatioN on 14.10.2014.
 */

//må teste delete mer for å se om den funker.
public class DeleteOrder extends AsyncTask<String, Void, String> {


    private Context context;

    public DeleteOrder(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        return APIManager.deleteOrder(Integer.parseInt(strings[0])) + "";
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
