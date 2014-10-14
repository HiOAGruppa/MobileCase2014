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
import java.util.LinkedList;
import java.util.List;

import main.mesanius.no.mobilecase2014.API.APIManager;
import main.mesanius.no.mobilecase2014.MainActivity;
import main.mesanius.no.mobilecase2014.Menu.MenuListItem;
import main.mesanius.no.mobilecase2014.Order.Order;
import main.mesanius.no.mobilecase2014.Order.OrderItem;
import main.mesanius.no.mobilecase2014.R;

public class OrderActivity extends Activity {
	private ListView listView;
	private ArrayList<OrderItemActivity> itemList;
    private ArrayList<MenuListItem> menuList;
    private List<Order> orders;
	private boolean call = true;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

        String JSONString = getIntent().getStringExtra("orders");
        orders = APIManager.getAllOrdersFromString(JSONString);
        //TextView priceView = (TextView) findViewById(R.id.priceTotalNumTextView);

        //LinkedList<Order> allOrders = new LinkedList<Order>();//APIManager.g
        Order testOrder = new Order(3,0);
        testOrder.addOrderItem(new OrderItem(3,2));
        testOrder.addOrderItem(new OrderItem(6,2));
        orders.add(testOrder);

        menuList = MainActivity.getMenuList();

        itemList = generateShoppingItems();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new OrderItemAdapter(this, itemList));



        final LinearLayout callScreen = (LinearLayout)findViewById(R.id.kelnerNeeded);
        if(call)  //when table presses "service"
        	callScreen.setVisibility(View.VISIBLE);
        
        Button button = (Button) findViewById(R.id.removeCall);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callScreen.setVisibility(View.GONE);
            }
        });
	}

    //converts orders to orderItemActivity
	private ArrayList<OrderItemActivity> generateShoppingItems(){

        ArrayList<OrderItemActivity> listOfItems = new ArrayList<OrderItemActivity>();

        OrderItemActivity itemForList = new OrderItemActivity(1,"Order 1", "Pizza:3\nBoller:1", 1);
        itemForList.setTableId(5);

        //to test-orders for orders from users and tables
        listOfItems.add(itemForList);//order from a table
        listOfItems.add(new OrderItemActivity(2,"Order 2", "Biff:2", 0,4));//order from an user(id:5)

        String desc = "";

        for(Order order : orders)
        {     //finds the name of all the items in an order
              for(OrderItem singleItem: order.getOrderItems())
              {
                  MenuListItem menuItem = getMenuItem(singleItem.getItemId());
                  desc+= menuItem.getTitle() + ": "+ singleItem.getQuantity()+ "\n";
              }
              OrderItemActivity orderItem = new OrderItemActivity(order.getOrderId(),"Order " +
              order.getOrderId(), desc,2); // all is get-at store
            listOfItems.add(orderItem);
        }
        return listOfItems;
    }

    public MenuListItem getMenuItem(int id)
    {
        for(MenuListItem item : menuList)
        {
            if(item.getId()==id)
                return item;
        }
        return null;
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
