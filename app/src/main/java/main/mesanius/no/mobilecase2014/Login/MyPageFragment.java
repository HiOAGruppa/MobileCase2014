package main.mesanius.no.mobilecase2014.Login;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import main.mesanius.no.mobilecase2014.R;

public class MyPageFragment extends Fragment {
	LinearLayout home, rest;

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
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}
}
