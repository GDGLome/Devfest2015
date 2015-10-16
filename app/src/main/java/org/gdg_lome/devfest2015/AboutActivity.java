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
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.adapter.ContributorAdapter;
import org.gdg_lome.devfest2015.adapter.SpeakerAdapter;
import org.gdg_lome.devfest2015.model.Contributor;
import org.gdg_lome.devfest2015.model.Speaker;

import java.util.ArrayList;


public class AboutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView list;
    private ContributorAdapter adapter;
    private ArrayList<Contributor> contributors = new ArrayList<Contributor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("A propos");

        list = (ListView) findViewById(R.id.list);
        adapter = new ContributorAdapter(this,contributors);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(contributors.get(position).getGplus()));
                startActivity(i);
                startActivity(i);
            }
        });

        Devfest2015Application.devfestBackend.child(Utils.BACKEND_CONTRIBUTOR_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                contributors.clear();
                for (DataSnapshot contributor : dataSnapshot.getChildren()) {
                    contributors.add(contributor.getValue(Contributor.class));
                }
                adapter.notifyDataSetChanged();
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
