package main.mesanius.no.mobilecase2014;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.mesanius.no.mobilecase2014.API.CallMenu;
import main.mesanius.no.mobilecase2014.Order.OrderFragment;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class OrderFrame extends Fragment {

    public OrderFrame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate PageFrame Layout som holder andre fragments
        View view = inflater.inflate(R.layout.order_frame, container, false);

        //Bytte ut det tomme Layoutet i PageFrame med MenyFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.order_frame, new OrderFragment(), "OrderFrag");
        transaction.commit();
        return view;
    }
}
