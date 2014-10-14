package main.mesanius.no.mobilecase2014.Kitchen;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import main.mesanius.no.mobilecase2014.API.DeleteOrder;
import main.mesanius.no.mobilecase2014.R;


public class OrderItemAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<OrderItemActivity> data;
	private static LayoutInflater inflater = null;
	
	public OrderItemAdapter(Context context, ArrayList<OrderItemActivity> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.order_line_layout, null);
        if(position>getCount())
        	return null;
        final OrderItemActivity item = data.get(position);
        
        final TextView orderId = (TextView)vi.findViewById(R.id.idField);
        orderId.setText(""+item.getId());
        
        TextView orderDesc = (TextView)vi.findViewById(R.id.orderContent);
        orderDesc.setText(item.getDesc());
        
        final Button deleteBtn = (Button)vi.findViewById(R.id.delete);
        final Button typeButton = (Button)vi.findViewById(R.id.typeBtn);
        
        deleteBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                new DeleteOrder().execute(item.getId());
				data.remove(position);
				notifyDataSetChanged();
			}
		});
        
        if(item.getType()==0)
        {
        	typeButton.setBackgroundColor(Color.RED);
        	typeButton.setText(""+ item.getPersonId());
        	typeButton.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				System.out.println("!!!!!! SHOW PERSON: " + item.getPersonId());
    			}
    		});
        }
        else if(item.getType()==1)
        {
        	typeButton.setBackgroundColor(Color.GREEN);
        	typeButton.setText(item.getTableId()+"");
        }
        else
        {
        	typeButton.setBackgroundColor(Color.BLUE);
        	typeButton.setText("");
        }
        //MAKE VIEW!!!
        return vi;
    }	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
