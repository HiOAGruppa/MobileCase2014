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

import main.mesanius.no.mobilecase2014.Order.OrderAdapter;
import main.mesanius.no.mobilecase2014.Order.OrderItem;
import main.mesanius.no.mobilecase2014.R;


public class OrderFragment extends Fragment{
	
	private ListView listView;
    private ArrayList<OrderItem> itemList = new ArrayList<OrderItem>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		 if (savedInstanceState != null) {
			 generateShoppingItems(itemList);
	     }
		 else{
			 generateShoppingItems(itemList);
		 }
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		updateArticleView();

	}

	public void updateArticleView() {
		TextView priceView = (TextView) getActivity().findViewById(R.id.totalPriceTextView);

        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(new OrderAdapter(getActivity(), itemList, priceView));

        Button button = (Button) getActivity().findViewById(R.id.buyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Woho, purchase
            }
        });
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		 
	}
	

    private void generateShoppingItems(ArrayList<OrderItem> items){
        String[] names = {"Meatballs", "Pasta", "Pizza", "Fish", "French Fries", "Stuff"};
        int[] price = {130, 120, 149, 180, 79, 99};

        for(int i = 0; i < price.length; i++)
            items.add(new OrderItem(i, 1));
    }

}
