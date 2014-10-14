package main.mesanius.no.mobilecase2014.Frames;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.mesanius.no.mobilecase2014.API.CallMenu;
import main.mesanius.no.mobilecase2014.Login.LoginFragment;
import main.mesanius.no.mobilecase2014.Login.MyPageFragment;
import main.mesanius.no.mobilecase2014.Login.TableFragment;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.Order.OrderFragment;
import main.mesanius.no.mobilecase2014.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class LoginFrame extends Fragment {
    public LoginFrame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate PageFrame Layout som holder andre fragments
        View view = inflater.inflate(R.layout.login_frame, container, false);

        //Bytte ut det tomme Layoutet i PageFrame med LoginFragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.login_frame, new LoginFragment(), "LoginFrag");
        transaction.commit();
        return view;
    }

    //Oppdatere fragmentet i loginframe til ved pålogging/pålogget tilstand
    public void updateFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (((MainActivity)getActivity()).user) {
            case 1:
                transaction.replace(R.id.login_frame, new MyPageFragment(), "MyPageFrag");
                break;
            case 2:
                transaction.replace(R.id.login_frame, new TableFragment(), "TableFrag");
                break;
            default:
                transaction.replace(R.id.login_frame, new LoginFragment(), "LoginFrag");

        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
