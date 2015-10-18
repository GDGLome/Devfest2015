package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.model.Attendee;
import org.gdg_lome.devfest2015.navigation.NavigationDrawerCallbacks;
import org.gdg_lome.devfest2015.navigation.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String attendee_id;
    private Attendee attendee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendee_id = Devfest2015Application.prefs.getString(Utils.PREFS_ATTENDEE_ID,null);
        setContentView(R.layout.activity_main);
        // Set up the drawer.
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        Devfest2015Application.devfestBackend
                .child(Utils.BACKEND_ATTENDEE_PATH)
                .child(attendee_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                attendee = dataSnapshot.getValue(Attendee.class);
                // populate the navigation drawer
                mNavigationDrawerFragment.setUserData(attendee.getName(), Utils.createQRCode(attendee.getBarcode().getBarcode()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);


    }


    private void setupViewPager(ViewPager viewPager) {
        ScheduleFragment conferencesFragment = new ScheduleFragment();
        Bundle conference_extras = new Bundle();
        conference_extras.putString(Utils.SCHEDULE_ID,Utils.BACKEND_CONFERENCE_PATH);
        conferencesFragment.setArguments(conference_extras);
        ScheduleFragment codelabsFragment = new ScheduleFragment();
        Bundle codelab_extras = new Bundle();
        codelab_extras.putString(Utils.SCHEDULE_ID,Utils.BACKEND_CODELAB_PATH);
        codelabsFragment.setArguments(codelab_extras);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(conferencesFragment, "Conférences \n 24 Oct. 2015");
        adapter.addFrag(codelabsFragment, "CodeLabs\n" +
                " 25 Oct. 2015");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(this,PresentationActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,SpeakerActivity.class));
                break;
            case 2: startActivity(new Intent(this,SponsorActivity.class));
                break;
            case 3: startActivity(new Intent(this,AboutActivity.class));
                break;
        }

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }





}
