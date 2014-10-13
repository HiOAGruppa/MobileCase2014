package main.mesanius.no.mobilecase2014;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.mesanius.no.mobilecase2014.Menu.ItemFragment;
import main.mesanius.no.mobilecase2014.Menu.MenuFragment;
import main.mesanius.no.mobilecase2014.Menu.MenuListItem;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class PageFrame extends Fragment implements MenuFragment.OnHeadlineSelectedListener, ItemFragment.OnHeadlineSelectedListener {

    ItemFragment itemFragment;

    public PageFrame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate PageFrame Layout som holder andre fragments
        View view = inflater.inflate(R.layout.page_frame, container, false);

        //Bytte ut det tomme Layoutet i PageFrame med MenyFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.root_frame, new MenuFragment());
        transaction.commit();

        return view;
    }

    @Override
    public void onArticleSelected(int p, MenuListItem item) {
        //setMenuText(item.getTitle());

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
    }

    @Override
    public void returnToMenu() {
        //setMenuText("Meny");
        getFragmentManager().popBackStack();
    }
}
