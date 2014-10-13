package main.mesanius.no.mobilecase2014.Menu;

import android.app.Activity;
import android.app.ListFragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import main.mesanius.no.mobilecase2014.API.APIManager;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.R;

public class MenuFragment extends ListFragment {

	String[] menutitles;
	TypedArray menuIcons;

	MenuListAdapter adapter;
	private List<MenuListItem> menuListItems;

	OnHeadlineSelectedListener mCallback;

    public MenuFragment() {
    }

	public interface OnHeadlineSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void onArticleSelected(int p, MenuListItem i);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		menutitles = getResources().getStringArray(R.array.titles);
        if(savedInstanceState != null){
            String JSONMenu = savedInstanceState.getString("JSONMenu");
            menuListItems = APIManager.getMenuFromString(JSONMenu);
        }else{
            String JSONMenu = getArguments().getString("JSONMenu");
            menuListItems = APIManager.getMenuFromString(JSONMenu);
        }

        //original menuitemsList generation DELETE LATER
        /*
		MenuListItems = new ArrayList<MenuListItem>();

		for (int i = 0; i < menutitles.length; i++) {
			MenuListItem items = new MenuListItem(menutitles[i], R.drawable.ic_launcher,
					i, "En rett fra resturant menyen", 250.00);
			
			MenuListItems.add(items);
		}
        */
		adapter = new MenuListAdapter(getActivity(), menuListItems);
		setListAdapter(adapter);
	}

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

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		MenuListItem item = menuListItems.get(position);


		mCallback.onArticleSelected(position, item);
	}

}