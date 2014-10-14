package main.mesanius.no.mobilecase2014;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import main.mesanius.no.mobilecase2014.Login.LoginFragment;
import main.mesanius.no.mobilecase2014.Order.OrderFragment;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    //Variabler for å kontrollere tabnavigasjon
    private ViewPager viewPager;
    private ActionBar actionBar;

    //FrameLayout som fungerer som container for andre fragments
    public PageFrame pageFrame = new PageFrame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Oppretter og styrer ViewPager som håndterer swipes og bytte av tabs
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new VPAdapter(getFragmentManager(), pageFrame));
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
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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

//Custom ViewPager adapter. Styrer veksling mellom fragmenter ved swipe eller klikk på tab
class VPAdapter extends FragmentPagerAdapter {
    PageFrame frame;

    public VPAdapter(FragmentManager fm, PageFrame pageFrame) {
        super(fm);
        frame = pageFrame;
    }

    //Velger fragment ved swipe (implementert i Tablistener for å også kjøre ved valg av tab)
    @Override
    public Fragment getItem(int i) {
        Fragment fragment;

        switch (i) {
            case 1:
                fragment = new OrderFragment();
                break;
            case 2:
                fragment = new LoginFragment();
                break;
            default:
                fragment = frame;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}