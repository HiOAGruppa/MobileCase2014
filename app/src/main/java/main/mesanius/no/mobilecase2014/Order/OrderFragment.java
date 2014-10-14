package main.mesanius.no.mobilecase2014.Order;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;


public class OrderFragment extends Fragment{
	
	private ListView listView;
    private ArrayList<OrderListItem> itemList = new ArrayList<OrderListItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        itemList = ((MainActivity)getActivity()).getOrderList();

        // Inflate the layout for this fragment
		return inflater.inflate(R.layout.order, container, false);
	}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateOrderFragment();
    }


	public void updateOrderFragment() {
		TextView priceView = (TextView) getActivity().findViewById(R.id.totalPriceTextView);

        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(new OrderAdapter(getActivity(), itemList, priceView));

        Button button = (Button) getActivity().findViewById(R.id.buyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).clearOrderList();
                updateOrderFragment();
            }
        });

        if(itemList.isEmpty()) {
            getActivity().findViewById(R.id.listView).setVisibility(View.GONE);
            getActivity().findViewById(R.id.emptyOrderTextView).setVisibility(View.VISIBLE);
        }
        else {
            getActivity().findViewById(R.id.listView).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.emptyOrderTextView).setVisibility(View.GONE);
        }
	}


    @Override
    public void onResume() {
        super.onResume();
        updateOrderFragment();
    }

    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
