package main.mesanius.no.mobilecase2014.Login;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import main.mesanius.no.mobilecase2014.R;

public class MyPageFragment extends Fragment {
	OnHeadlineSelectedListener mCallback;
	LinearLayout home, rest;

	public interface OnHeadlineSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void payAndConfirm();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		if (savedInstanceState != null) {
		} else {

		}
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.my_page, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		updateArticleView();

	}

	public void updateArticleView() {
        final Button editUser = (Button)getActivity().findViewById(R.id.editUser);
        editUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "Edit user pressed", Toast.LENGTH_SHORT);
            }
        });
        final Button showOrders = (Button)getActivity().findViewById(R.id.showOrders);
        showOrders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "Show orders pressed", Toast.LENGTH_SHORT);
            }
        });
        final Button loggOut = (Button)getActivity().findViewById(R.id.goBack);
        loggOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "Logging out pressed", Toast.LENGTH_SHORT);
            }
        });
		
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

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
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

}
