package main.mesanius.no.mobilecase2014.Kitchen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.R;

public class OrderActivity extends Activity {
	 private ListView listView;
	 private ArrayList<OrderItemActivity> itemList = new ArrayList<OrderItemActivity>();
	 private boolean callKelner = true;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

        generateShoppingItems(itemList);
        //TextView priceView = (TextView) findViewById(R.id.priceTotalNumTextView);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new OrderItemAdapter(this, itemList));
        
        
        final LinearLayout callScreen = (LinearLayout)findViewById(R.id.kelnerNeeded);
        if(callKelner)
        	callScreen.setVisibility(View.VISIBLE);
        
        Button button = (Button) findViewById(R.id.removeCall);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callScreen.setVisibility(View.GONE);
            }
        });
	}

	private void generateShoppingItems(ArrayList<OrderItemActivity> items){

        for(int i = 0; i < 2; i++)
            items.add(new OrderItemActivity(i,"Order 2", "Boller\n Nisser \n annet", 2));
        items.add(new OrderItemActivity(2,"Order 4", "Boller\n Nisser \n annet", 0,4));
        for(int i = 3; i < 6; i++)
        {
            OrderItemActivity item = new OrderItemActivity(i,"Order 1", "Boller\n Nisser \n annet\n annet\n annet", 1);
        	item.setTableId(5);
        	items.add(item);
        }
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
