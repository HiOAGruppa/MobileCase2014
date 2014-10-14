package main.mesanius.no.mobilecase2014;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.Login.LoginFragment;
import main.mesanius.no.mobilecase2014.Order.OrderFragment;
import main.mesanius.no.mobilecase2014.Order.OrderListItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    //Variabler for å kontrollere tabnavigasjon
    private ViewPager viewPager;
    private ActionBar actionBar;

    //FrameLayout som fungerer som container for andre fragments
    public MenuFrame menuFrame = new MenuFrame();
    public OrderFrame orderFrame = new OrderFrame();

    private ArrayList<OrderListItem> orderList = new ArrayList<OrderListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Oppretter og styrer ViewPager som håndterer swipes og bytte av tabs
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new VPAdapter(getFragmentManager(), menuFrame, orderFrame));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //Obligatoriske metoder for ViewPager
            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrolled(int i, float v, int i2) { }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        //Legger til tabs i actionbar
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(
                actionBar.newTab()
                        .setText(getString(R.string.title_section1))
                        .setTabListener(this)
        );
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getString(R.string.title_section2))
                        .setTabListener(this)
        );
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getString(R.string.title_section3))
                        .setTabListener(this)
        );
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            finish();
    }

    //Obligatoriske metoder for Actionbar.Tablistener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
        getFragmentManager().popBackStack();
        if(tab.getPosition() == 1)
            getFragmentManager().findFragmentByTag("OrderFrag").onResume();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        getFragmentManager().popBackStack();
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

    //OrderListMetoder
    public ArrayList<OrderListItem> getOrderList(){
        return orderList;
    }

    public boolean addToOrderList(OrderListItem item){
        Log.d("Added orderItem: ", item.getName());
        for (OrderListItem i: orderList) {
            if (i.getName().equals(item.getName())){
                i.setQuantity(true);
                return true;
            }
        }
        return orderList.add(item);

    }

    public boolean removeFromOrderList(OrderListItem item){
        return orderList.remove(item);
    }

    public String getToStringList()
    {
        StringBuilder outString = new StringBuilder();
        double price = 0;

        for(OrderListItem item: orderList)
        {
            outString.append(item.getName()).append("\t").append(item.getQuantity())
                    .append("\t").append(item.getPrice()).append("kr\n");
            price +=item.getPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        outString.append("\n\n").append("Totalt:\t\t\t").append(df.format(price)).append("kr");

        return outString.toString();
    }


    public void clearOrderList(){
        orderList.clear();
    }
}

//Custom ViewPager adapter. Styrer veksling mellom fragmenter ved swipe eller klikk på tab
class VPAdapter extends FragmentPagerAdapter {
    MenuFrame menuFrame;
    OrderFrame orderFrame;

    public VPAdapter(FragmentManager fm, MenuFrame menuFrame, OrderFrame orderFrame) {
        super(fm);
        this.menuFrame = menuFrame;
        this.orderFrame = orderFrame;
    }

    //Velger fragment ved swipe (implementert i Tablistener for å også kjøre ved valg av tab)
    @Override
    public Fragment getItem(int i) {
        Fragment fragment;

        switch (i) {
            case 1:
                fragment = orderFrame;
                break;
            case 2:
                fragment = new LoginFragment();
                break;
            default:
                fragment = menuFrame;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}