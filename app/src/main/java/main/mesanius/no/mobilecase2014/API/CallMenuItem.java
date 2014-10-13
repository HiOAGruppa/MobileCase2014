package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import main.mesanius.no.mobilecase2014.Menu.MenuItem;

/**
 * Created by NegatioN on 12.10.2014.
 */
public class CallMenuItem extends AsyncTask<String, String, MenuItem> {

    public final static String INTENT_NAME = "intent_name", INTENT_DESC = "intent_desc", INTENT_PRICE = "intent_price", INTENT_ID = "intent_id";


    private Context context;

    public CallMenuItem(Context context){
        this.context = context;
    }


    //will return arraylist of length= 1 for menuitems, returns normal list for full menu. (Attach some sort of end-node to
    //indicate if list or single item if menulength is 1!!!
    protected MenuItem doInBackground(String... strings) {
        String JSONdata = APIManager.fetchJSON(strings);

        return APIManager.getMenuItemFromString(JSONdata);

    }

    @Override
    protected void onPostExecute(MenuItem result) {

        //her kan vi starte fragmentet vil jeg tro. Send med intent-data
        Intent i = new Intent(context, Mesanius.class);

        //send the three info-fields of a single menuitem.
        i.putExtra(INTENT_NAME, result.getName());
        i.putExtra(INTENT_DESC, result.getDescription());
        i.putExtra(INTENT_PRICE, result.getPrice());
        i.putExtra(INTENT_ID, result.getId());

        //not needed for fragment-start
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);

    }


}
