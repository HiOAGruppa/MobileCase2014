package main.mesanius.no.mobilecase2014.Order;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;

import main.mesanius.no.mobilecase2014.API.PostOrder;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;

public class OrderSettingsFragment extends Fragment {
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
		return inflater.inflate(R.layout.order_settings, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		updateArticleView();

	}

	public void updateArticleView() {

		home = (LinearLayout) getActivity().findViewById(
				R.id.homeSelected);
		rest = (LinearLayout) getActivity().findViewById(
				R.id.restSelected);
		final Button ok = (Button)getActivity().findViewById(R.id.Order);
		ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showActivityOverlay();
            }
        });
		
		RadioButton radioRest = (RadioButton)getActivity().findViewById(R.id.ResturantRB);
		radioRest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeView(false);
			}
		});
		RadioButton radioHome = (RadioButton)getActivity().findViewById(R.id.HomeRB);
		radioHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeView(true);
			}
		});
	}
    private void showActivityOverlay() {

        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.receipt);

        TextView receiptText = (TextView)dialog.findViewById(R.id.reciptString);
        receiptText.setText(this.getReceipt());
        receiptText.setTextColor(Color.BLACK);

        final Button btn = (Button)dialog.findViewById(R.id.confirmBtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //add order
                new PostOrder().execute(((MainActivity) getActivity()).convertOrder());
                getFragmentManager().popBackStack();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
	public void changeView(boolean b)
	{
		if (b) {
			home.setVisibility(View.VISIBLE);
			rest.setVisibility(View.GONE);
		}
		else
		{
			home.setVisibility(View.GONE);
			rest.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

    public String getReceipt()
    {
        return ((MainActivity)getActivity()).getToStringList();
    }

}
