package main.mesanius.no.mobilecase2014.Menu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import main.mesanius.no.mobilecase2014.R;

public class MenuListAdapter extends BaseAdapter {

    Context context;
    List<MenuListItem> menuListItem;

    MenuListAdapter(Context context, List<MenuListItem> menuListItem) {
        this.context = context;
        this.menuListItem = menuListItem;
    }

    @Override
    public int getCount() {

        return menuListItem.size();
    }

    @Override
    public Object getItem(int position) {

        return menuListItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return menuListItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.menu_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        
        MenuListItem row_pos = menuListItem.get(position);
        
        // setting the image resource and title
        imgIcon.setImageResource(row_pos.getIcon());
        txtTitle.setText(row_pos.getTitle());
        
        //NumberFormat formatter = NumberFormat.getCurrencyInstance();
        //String moneyString = formatter.format(row_pos.getPrice());
        DecimalFormat df = new DecimalFormat("#.##");
        txtPrice.setText(df.format(row_pos.getPrice())+"kr");

        return convertView;

    }

}