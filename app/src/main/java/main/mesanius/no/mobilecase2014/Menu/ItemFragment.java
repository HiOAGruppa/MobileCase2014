package main.mesanius.no.mobilecase2014.Menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;

public class ItemFragment extends Fragment {

    int mCurrentPosition = -1;
    private String title, desc;
    private int img;
    private double price;

	OnHeadlineSelectedListener mCallback;

	public interface OnHeadlineSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void returnToMenu();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		 if (savedInstanceState != null) {

             //VIA REST
             /*Intent intent = getActivity().getIntent();

             title = intent.getStringExtra(CallMenuItem.INTENT_NAME);
             desc = intent.getStringExtra(CallMenuItem.INTENT_DESC);
             price = intent.getIntExtra(CallMenuItem.INTENT_PRICE, 0);

             new CallMenuItem(getActivity().getApplicationContext()).execute("1");*/

             title = savedInstanceState.getString("title");
             desc = savedInstanceState.getString("desc");
             price = savedInstanceState.getDouble("price");
             img = savedInstanceState.getInt("image");
             mCurrentPosition = savedInstanceState.getInt("id");
	        }
		 else
		 {
             //VIA REST
             /*Intent intent = getActivity().getIntent();

             title = intent.getStringExtra(CallMenuItem.INTENT_NAME);
             desc = intent.getStringExtra(CallMenuItem.INTENT_DESC);
             price = intent.getIntExtra(CallMenuItem.INTENT_PRICE, 0);

             new CallMenuItem(getActivity().getApplicationContext()).execute("1");

             img = getArguments().getInt("image");
             mCurrentPosition = getArguments().getInt("id");*/

            mCurrentPosition = getArguments().getInt("id");
		 	title = getArguments().getString("title");
		 	desc = getArguments().getString("desc");
		 	img = getArguments().getInt("image");
		 	price = getArguments().getDouble("price");
		 }
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.item_details, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		updateArticleView();

	}

	public void updateArticleView() {
		TextView title = (TextView) getActivity().findViewById(R.id.itemTitle);
		title.setText(this.title);
		
		TextView itemDesc= (TextView)getActivity().findViewById(R.id.itemDesc);
		itemDesc.setText(desc);
		
		TextView itemPrice= (TextView)getActivity().findViewById(R.id.itemPrice);
		DecimalFormat df = new DecimalFormat("#.##");
		itemPrice.setText(df.format(price) + "kr");
		
		ImageView imgage = (ImageView)getActivity().findViewById(R.id.menuItemImage);
		imgage.setImageResource(img);
		
		final Button order = (Button) getActivity().findViewById(R.id.itemOrderBtn);

		order.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("ShowToast") @Override
			public void onClick(View v) {
				//mCallback.returnToMenu();
                Log.d("ORDER", "Order item: " + mCurrentPosition);
			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		 outState.putInt("id", mCurrentPosition);
		 outState.putInt("img", img);
		 outState.putString("title",title);
		 outState.putString("desc", desc);
		 outState.putDouble("price", price);
	}

	/*
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { // set the layout you want to
	 * display in First Fragment View view = inflater.inflate(R.layout.testfrag,
	 * container, false); return view;
	 * 
	 * }
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
            mCallback = ((MainActivity)activity).pageFrame;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	
}
