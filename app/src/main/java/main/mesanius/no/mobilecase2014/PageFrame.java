package main.mesanius.no.mobilecase2014;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.mesanius.no.mobilecase2014.API.CallMenu;
import main.mesanius.no.mobilecase2014.API.CallMenuItem;
import main.mesanius.no.mobilecase2014.Menu.ItemFragment;
import main.mesanius.no.mobilecase2014.Menu.MenuFragment;
import main.mesanius.no.mobilecase2014.Menu.MenuListItem;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class PageFrame extends Fragment {

    public PageFrame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate PageFrame Layout som holder andre fragments
        View view = inflater.inflate(R.layout.page_frame, container, false);

        //Bytte ut det tomme Layoutet i PageFrame med MenyFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //get menu from API and generate menufragment
        new CallMenu(transaction).execute("menugen");

        //included in CallMenu
     //   transaction.replace(R.id.root_frame, new MenuFragment());
      //  transaction.commit();

        return view;
    }

    public void onArticleSelected(int p, MenuListItem item) {
        //setMenuText(item.getTitle());

        //FLAGGED FOR DELETEION BEFORE Hand-in!
        //ORIGINAL METHOD!!
        /*
        itemFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt("id",p);
        args.putString("title", item.getTitle());
        args.putString("desc", item.getDesc());
        args.putDouble("price", item.getPrice());
        args.putInt("image",item.getIcon());
        itemFragment.setArguments(args);


        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.root_frame, itemFragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        Log.d("BACKSTACK:", "" + getFragmentManager().getBackStackEntryCount());
        */

        //ORIGINAL METHOD END

    }
}
