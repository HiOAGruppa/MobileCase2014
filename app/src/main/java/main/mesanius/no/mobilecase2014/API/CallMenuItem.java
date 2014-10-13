package main.mesanius.no.mobilecase2014.API;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import main.mesanius.no.mobilecase2014.Menu.ItemFragment;
import main.mesanius.no.mobilecase2014.Menu.MenuItem;
import main.mesanius.no.mobilecase2014.R;

/**
 * Created by NegatioN on 12.10.2014.
 */
public class CallMenuItem extends AsyncTask<String, String, MenuItem> {

    public final static String INTENT_NAME = "intent_name", INTENT_DESC = "intent_desc", INTENT_PRICE = "intent_price", INTENT_ID = "intent_id";


    private Context context;
    private FragmentTransaction fragmentTransaction;
    private ItemFragment itemFragment;

    public CallMenuItem(FragmentTransaction fragmentTransaction, ItemFragment itemFragment){
        this.fragmentTransaction = fragmentTransaction;
        this.itemFragment = itemFragment;
    }


    //will return arraylist of length= 1 for menuitems, returns normal list for full menu. (Attach some sort of end-node to
    //indicate if list or single item if menulength is 1!!!
    protected MenuItem doInBackground(String... strings) {
        String JSONdata = APIManager.fetchJSON(strings);

        return APIManager.getMenuItemFromString(JSONdata);

    }

    @Override
    protected void onPostExecute(MenuItem result) {
        itemFragment = new ItemFragment();

        //generate bundle for start of itemfragment
        Bundle args = new Bundle();
        args.putInt("id",result.getId());
        args.putString("title", result.getName());
        args.putString("desc", result.getDescription());
        args.putDouble("price", result.getPrice());
        args.putInt("image", R.drawable.ic_launcher);
        itemFragment.setArguments(args);


        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        fragmentTransaction.replace(R.id.root_frame, itemFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();


    }


}
