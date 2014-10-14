package main.mesanius.no.mobilecase2014.Menu;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import main.mesanius.no.mobilecase2014.API.APIManager;
import main.mesanius.no.mobilecase2014.API.CallMenuItem;
import main.mesanius.no.mobilecase2014.API.CallOrders;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;

public class MenuFragment extends ListFragment {

	TypedArray menuIcons;

	MenuListAdapter adapter;
	private List<MenuListItem> menuListItems;

    public MenuFragment() {
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            String JSONMenu = savedInstanceState.getString("JSONMenu");
            menuListItems = APIManager.getMenuFromString(JSONMenu);
        }else{
            String JSONMenu = getArguments().getString("JSONMenu");
            menuListItems = APIManager.getMenuFromString(JSONMenu);
            ((MainActivity)getActivity()).addToMenuList(menuListItems);
        }

        new CallOrders().execute();

		adapter = new MenuListAdapter(getActivity(), menuListItems);
		setListAdapter(adapter);
        ((MainActivity)getActivity()).setMenuFragment(this);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// Notify the parent activity of selected item
		MenuListItem item = menuListItems.get(position);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        ItemFragment itemFragment = new ItemFragment();

        new CallMenuItem(transaction, itemFragment).execute(String.valueOf(item.getId()));
	}

    public void notifyChange() {
        adapter.notifyDataSetChanged();
    }
}