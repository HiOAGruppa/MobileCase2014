package main.mesanius.no.mobilecase2014.Order;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.API.PostOrder;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.Menu.ItemFragment;
import main.mesanius.no.mobilecase2014.R;


public class OrderFragment extends Fragment {

    private ListView listView;
    private ArrayList<OrderListItem> itemList = new ArrayList<OrderListItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        itemList = ((MainActivity) getActivity()).getOrderList();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateOrderFragment();
    }

    private void showActivityOverlay() {

        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.receipt);

        TextView receiptText = (TextView)dialog.findViewById(R.id.reciptString);
        receiptText.setText(((MainActivity)getActivity()).getToStringList());
        receiptText.setTextColor(Color.BLACK);

        final Button btn = (Button)dialog.findViewById(R.id.confirmBtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //add order
                new PostOrder().execute(((MainActivity)getActivity()).convertOrder());
                updateOrderFragment();
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    public void updateOrderFragment() {
        TextView priceView = (TextView) getActivity().findViewById(R.id.totalPriceTextView);

        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(new OrderAdapter(getActivity(), itemList, priceView));

        Button button = (Button) getActivity().findViewById(R.id.buyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).getOrderList().isEmpty()) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Tom bestilling.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (((MainActivity) getActivity()).user == MainActivity.USER_NONE) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Logg inn for å bestille.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (((MainActivity) getActivity()).user == MainActivity.USER_TABLE) {
                    showActivityOverlay();
                }
                else{
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.order_frame, new OrderSettingsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            }
        });

        if (itemList.isEmpty()) {
            getActivity().findViewById(R.id.listView).setVisibility(View.GONE);
            getActivity().findViewById(R.id.emptyOrderTextView).setVisibility(View.VISIBLE);
        } else {
            getActivity().findViewById(R.id.listView).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.emptyOrderTextView).setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
