package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.adapter.SponsorAdapter;
import org.gdg_lome.devfest2015.model.Sponsor;

import java.util.ArrayList;


public class SponsorActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ArrayList<Sponsor> sponsors;
    private SponsorFragment sponsorFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Les sponsors");


        Devfest2015Application.devfestBackend.child(Utils.BACKEND_SPONSOR_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //general
                sponsors = new ArrayList<Sponsor>();
                for(DataSnapshot sponsor:dataSnapshot.child(Utils.BACKEND_SPONSOR_GENERAL_PATH).getChildren()){
                    sponsors.add(sponsor.getValue(Sponsor.class));
                }
                if (!sponsors.isEmpty()){
                    sponsorFragment = new SponsorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utils.SPONSOR_EXTRA, sponsors);
                    bundle.putString(Utils.SPONSOR_ID, Utils.BACKEND_SPONSOR_GENERAL_PATH);
                    sponsorFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.general,sponsorFragment)
                            .commit();
                }

                //platinium
                sponsors = new ArrayList<Sponsor>();
                for(DataSnapshot sponsor:dataSnapshot.child(Utils.BACKEND_SPONSOR_PLATINIUM_PATH).getChildren()){
                    sponsors.add(sponsor.getValue(Sponsor.class));
                }
                if (!sponsors.isEmpty()){
                    sponsorFragment = new SponsorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utils.SPONSOR_EXTRA, sponsors);
                    bundle.putString(Utils.SPONSOR_ID, Utils.BACKEND_SPONSOR_PLATINIUM_PATH);
                    sponsorFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.platinium, sponsorFragment)
                            .commit();
                }

                //gold
                sponsors = new ArrayList<Sponsor>();
                for(DataSnapshot sponsor:dataSnapshot.child(Utils.BACKEND_SPONSOR_GOLD_PATH).getChildren()){
                    sponsors.add(sponsor.getValue(Sponsor.class));
                }
                if (!sponsors.isEmpty()){
                    sponsorFragment = new SponsorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utils.SPONSOR_EXTRA, sponsors);
                    bundle.putString(Utils.SPONSOR_ID, Utils.BACKEND_SPONSOR_GOLD_PATH);
                    sponsorFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.gold,sponsorFragment)
                            .commit();
                }

                //silver
                sponsors = new ArrayList<Sponsor>();
                for(DataSnapshot sponsor:dataSnapshot.child(Utils.BACKEND_SPONSOR_SILVER_PATH).getChildren()){
                    sponsors.add(sponsor.getValue(Sponsor.class));
                }
                if (!sponsors.isEmpty()){
                    sponsorFragment = new SponsorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utils.SPONSOR_EXTRA, sponsors);
                    bundle.putString(Utils.SPONSOR_ID, Utils.BACKEND_SPONSOR_SILVER_PATH);
                    sponsorFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.silver,sponsorFragment)
                            .commit();
                }

                //bronze
                sponsors = new ArrayList<Sponsor>();
                for(DataSnapshot sponsor:dataSnapshot.child(Utils.BACKEND_SPONSOR_BRONZE_PATH).getChildren()){
                    sponsors.add(sponsor.getValue(Sponsor.class));
                }
                if (!sponsors.isEmpty()){
                    sponsorFragment = new SponsorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utils.SPONSOR_EXTRA, sponsors);
                    bundle.putString(Utils.SPONSOR_ID,Utils.BACKEND_SPONSOR_BRONZE_PATH);
                    sponsorFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.bronze,sponsorFragment)
                            .commit();
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if(id==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
