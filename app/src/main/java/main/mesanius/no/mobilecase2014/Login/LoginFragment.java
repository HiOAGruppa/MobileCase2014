package main.mesanius.no.mobilecase2014.Login;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        updateArticleView();

    }

    public void updateArticleView() {
        final Button loggInn = (Button) getActivity().findViewById(R.id.getButton);
        loggInn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Toast.makeText(getActivity(), "Logged Inn", Toast.LENGTH_SHORT);
                Log.d("SJEKK", ((MainActivity)getActivity()).getToStringList());
            }
        });
        final Button regUser = (Button) getActivity().findViewById(R.id.regUser);
        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
    }


}
