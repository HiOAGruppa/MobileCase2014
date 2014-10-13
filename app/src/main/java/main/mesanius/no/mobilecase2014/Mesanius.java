package main.mesanius.no.mobilecase2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Mesanius extends Activity {

    private Button nextItem;
    private TextView displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesanius);

        Intent intent = getIntent();

        String itemName = intent.getStringExtra(CallMenuItem.INTENT_NAME);
        String itemDesc = intent.getStringExtra(CallMenuItem.INTENT_DESC);
        int itemPrice = intent.getIntExtra(CallMenuItem.INTENT_PRICE, 0);
        int itemId = intent.getIntExtra(CallMenuItem.INTENT_ID,0);

        nextItem = (Button) findViewById(R.id.bNextItem);
        displayName = (TextView) findViewById(R.id.tvNameDisplay);

        if(itemName != null) {
            displayName.setText(itemName + "\n" + itemDesc + "\n" + itemPrice + "\n" + itemId);

        }

        nextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call denne metoden med menu-id for get spesifikt item, eller med tom string for hele menyen.
                new CallMenuItem(getApplicationContext()).execute("1");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mesanius, menu);
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
