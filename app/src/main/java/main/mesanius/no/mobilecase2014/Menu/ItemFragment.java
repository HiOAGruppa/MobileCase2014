package main.mesanius.no.mobilecase2014.Menu;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.Order.OrderListItem;
import main.mesanius.no.mobilecase2014.R;

public class ItemFragment extends Fragment {

    int id = -1;
    private String name, desc;
    private int img;
    private double price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            name = savedInstanceState.getString("title");
            desc = savedInstanceState.getString("desc");
            price = savedInstanceState.getDouble("price");
            img = savedInstanceState.getInt("image");
            id = savedInstanceState.getInt("id");
        } else {
            id = getArguments().getInt("id");
            name = getArguments().getString("title");
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
        final MainActivity activity = (MainActivity)getActivity();

        final TextView title = (TextView) activity.findViewById(R.id.itemTitle);
        title.setText(name);

        final TextView itemDesc = (TextView) activity.findViewById(R.id.itemDesc);
        itemDesc.setText(desc);

        TextView itemPrice = (TextView) activity.findViewById(R.id.itemPrice);
        DecimalFormat df = new DecimalFormat("#.##");
        itemPrice.setText(df.format(price) + ",-");

        ImageView image = (ImageView) activity.findViewById(R.id.menuItemImage);
        int imgid = activity.getResources().getIdentifier("img_" + id, "drawable", activity.getPackageName());
        image.setImageResource(imgid);


        final Button order = (Button) activity.findViewById(R.id.itemOrderBtn);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addToOrderList(new OrderListItem(id, name, price, 1));
                Toast toast = Toast.makeText(activity.getApplicationContext(), name + " lagt til.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", id);
        outState.putInt("img", img);
        outState.putString("title", name);
        outState.putString("desc", desc);
        outState.putDouble("price", price);
    }
}
