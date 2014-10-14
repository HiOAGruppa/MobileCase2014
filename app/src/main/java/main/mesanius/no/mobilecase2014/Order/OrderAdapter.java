package main.mesanius.no.mobilecase2014.Order;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import main.mesanius.no.mobilecase2014.R;

public class OrderAdapter extends BaseAdapter{
    private Context context;
    private int limit;
    private double totalPrice = 0;
    private TextView totalPriceTextView;
    private ArrayList<OrderListItem> data;
    private static LayoutInflater inflater = null;

    public OrderAdapter(Context context, ArrayList<OrderListItem> data, TextView priceView) {
        this.context = context;
        this.data = data;
        this.limit = data.size();
        this.totalPriceTextView = priceView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for(OrderListItem item : data){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public void updateTotalPrice() {
        getTotalPrice();
        totalPriceTextView.setText(totalPrice + ",-");
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public OrderListItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public OrderListItem getItem(String name) {
        for(OrderListItem item : data)
            if(item.getName().equals(name)) return item;
        return null;
    }

    public void addOrderItem(OrderListItem item) {
        OrderListItem oldItem = getItem(item.getName());
        if(oldItem == null) { // not already in cart
            data.add(item);
        }
        else {
            oldItem.setQuantity(true);
        }

        notifyDataSetChanged();
        updateTotalPrice();
    }

    public void updateRowItem(View view, boolean increment) {
        Button b = (Button) view;
        OrderListItem item = getItem(b.getTag().toString());
        item.setQuantity(increment);

        if(item.getQuantity() == 0)
            data.remove(item);
        if(data.isEmpty());
            //Vis tom-melding

        notifyDataSetChanged();
        updateTotalPrice();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.order_item, null);

        TextView textname = (TextView) vi.findViewById(R.id.itemNameTextView);
        textname.setText(data.get(position).getName());
        TextView textprice = (TextView) vi.findViewById(R.id.priceTextView);
        textprice.setText(data.get(position).getTotalPrice()+",-");
        TextView textquan = (TextView) vi.findViewById(R.id.quantityTextView);
        textquan.setText(data.get(position).getQuantity()+"stk");

        updateTotalPrice();

        Button bMinus = (Button) vi.findViewById(R.id.minusButton);
        bMinus.setTag(data.get(position).getName());
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRowItem(v, false);
            }
        });

        Button bPlus = (Button) vi.findViewById(R.id.plusButton);
        bPlus.setTag(data.get(position).getName());
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRowItem(v, true);
            }
        });

        return vi;
    }
}
